import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectStreamException;
import java.util.ArrayList;

class Pelanggan {
  private String nama;
  public Pelanggan(String nama) {
    this.nama = nama;
  }
  public String getNama() {
    return nama;
  }
  @Override
  public String toString() {
    return nama;
  }
}

class Pegawai {
  private String nama;
  public Pegawai(String nama) {
    this.nama = nama;
  }
  public String getNama() {
    return nama;
  }
  @Override
  public String toString() {
    return nama;
  }
}

class PS {
  private String kodePS;
  private String tipePS;
  public PS(String kodePS, String tipePS) {
    this.kodePS = kodePS;
    this.tipePS = tipePS;
  }
  public String getKodePS() {
    return kodePS;
  }
  public String getTipePS() {
    return tipePS;
  }
  @Override
  public String toString() {
    return tipePS;
  }
}

class Sewa {
  private Pelanggan pelanggan;
  private Pegawai pegawai;
  private PS ps;
  private double harga;
  private String tanggalSewa;
  private  String tanggalKembali;
  private int durasi;
  private double totalBiaya;
  public Sewa(Pelanggan pelanggan, Pegawai pegawai, PS ps, double harga, String tanggalSewa, String tanggalKembali, int durasi) {
    this.pelanggan = pelanggan;
    this.pegawai = pegawai;
    this.ps = ps;
    this.harga = harga;
    this.tanggalSewa = tanggalSewa;
    this.tanggalKembali = tanggalKembali;
    this.durasi = durasi;
  }
  public Pelanggan getPelanggan() {
    return pelanggan;
  }
  public Pegawai getPegawai() {
    return pegawai;
  }
  public PS getPs() {
    return ps;
  }
  public double getHarga() {
    return harga;
  }
  public String getTanggalSewa() {
    return tanggalSewa;
  }
  public String getTanggalKembali() {
    return tanggalKembali;
  }
  public int getDurasi() {
    return durasi;
  }
  public double getTotalBiaya() {
    return getHarga() * getDurasi();
  }
  @Override
  public String toString() {
    return "Nota:\n" +
            "Pelanggan: " + pelanggan.toString() +
            "\nPegawai: " + pegawai.toString() +
            "\nTipe PS: " + ps.toString() +
            "\nTanggal Sewa: " + tanggalSewa +
            "\nTanggal Kembali: " + tanggalKembali +
            "\nLama Sewa: " + durasi + " hari" +
            "\nTotal Harga: Rp." + getTotalBiaya();
  }
}

public class MainFrame extends JFrame {
  private JPanel menuPanel;
  private JTabbedPane tabPanel;
  private JPanel notaPanel;
  private JPanel riwayatPanel;
  private JPanel kelolaPanel;
  private JTextField inputPelanggan;
  private JLabel labelPelanggan;
  private JTextField inputAdmin;
  private JLabel labelAdmin;
  private JLabel tipeLabel;
  private JLabel kodeLabel;
  private JLabel hargaLabel;
  private JLabel tanggalSewaLabel;
  private JLabel tanggalKembaliLabel;
  private JLabel durasiLabel;
  private JComboBox boxKode;
  private JComboBox boxTipe;
  private JTextField inputHarga;
  private JTextField inputTanggalSewa;
  private JTextField inputTanggalKembali;
  private JTextField inputDurasi;
  private JButton cetakBtn;
  private JTable riwayatTabel;
  private JButton tambahBtn;
  private JButton hapusBtn;
  private JTable psTabel;

  private ArrayList<PS> psList = new ArrayList<>();
  public MainFrame() {
    setContentPane(menuPanel);
    setTitle("APLIKASI RENTAL PS");
    setSize(600,400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setFont(new Font("Poppins", Font.PLAIN, 14));
    //kelola ps
    DefaultTableModel psTabelModel = new DefaultTableModel(new Object[]{"Kode PS", "Tipe PS"}, 0);
    psTabel.setModel(psTabelModel);
    //inisialisasi ps
    PS ps1 = new PS("kode1", "PlayStation1");
    PS ps2 = new PS("kode2", "PlayStation2");
    PS ps3 = new PS("kode3", "PlayStation3");
    PS ps4 = new PS("kode4", "PlayStation4");
    psList.add(ps1);
    psList.add(ps2);
    psList.add(ps3);
    psList.add(ps4);
    boxKode.addItem(ps1.getKodePS());
    boxKode.addItem(ps2.getKodePS());
    boxKode.addItem(ps3.getKodePS());
    boxKode.addItem(ps4.getKodePS());
    boxTipe.addItem(ps1.getTipePS());
    boxTipe.addItem(ps2.getTipePS());
    boxTipe.addItem(ps3.getTipePS());
    boxTipe.addItem(ps4.getTipePS());
    DefaultTableModel modelAwal = (DefaultTableModel) psTabel.getModel();
    Object[] headerPS = {"Kode PS", "Tipe PS"};
    Object[] rowData1 = {ps1.getKodePS(), ps1.getTipePS()};
    Object[] rowData2 = {ps2.getKodePS(), ps2.getTipePS()};
    Object[] rowData3 = {ps3.getKodePS(), ps3.getTipePS()};
    Object[] rowData4 = {ps4.getKodePS(), ps4.getTipePS()};
    modelAwal.addRow(headerPS);
    modelAwal.addRow(rowData1);
    modelAwal.addRow(rowData2);
    modelAwal.addRow(rowData3);
    modelAwal.addRow(rowData4);

    //tambah ps
    tambahBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String kode = JOptionPane.showInputDialog("Masukkan kode ps: ");
        String tipe = JOptionPane.showInputDialog("Masukkan tipe ps: ");
        if (tipe != null && kode != null) {
          PS ps = new PS(kode, tipe);
          psList.add(ps);
          boxKode.addItem(kode);
          boxTipe.addItem(tipe);
          Object[] rowData = {ps.getKodePS(), ps.getTipePS()};
          DefaultTableModel model = (DefaultTableModel) psTabel.getModel();
          model.addRow(rowData);
        }
      }
    });
    //hapus
    hapusBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String kode = JOptionPane.showInputDialog("Masukkan Kode PS yang akan dihapus:");
        if (kode != null) {
          // Cari dan hapus PS dari ArrayList
          PS psToDelete = null;
          for (PS ps : psList) {
            if (ps.getKodePS().equals(kode)) {
              psToDelete = ps;
              break;
            }
          }
          if (psToDelete != null) {
            psList.remove(psToDelete);
            // Hapus data dari ComboBox Tipe PS dan Kode PS
            boxTipe.removeItem(psToDelete.getTipePS());
            boxKode.removeItem(kode);

            // Hapus data dari tabel
            DefaultTableModel model = (DefaultTableModel) psTabel.getModel();
            for (int row = 0; row < model.getRowCount(); row++) {
              if (model.getValueAt(row, 0).equals(kode)) {
                model.removeRow(row);
                break;
              }
            }
          }


        }
      }
    });

    //nota
    DefaultTableModel riwayatTabelModel = new DefaultTableModel(new Object[]{
            "Pelanggan",
            "Pegawai",
            "Tipe PS",
            "Tanggal Sewa",
            "Tanggal Kembali",
            "Durasi Sewa",
            "Total Harga"
    }, 0);
    riwayatTabel.setModel(riwayatTabelModel);
    Object[] riwayatHeader = {
            "Pelanggan",
            "Pegawai",
            "Tipe PS",
            "Tanggal Sewa",
            "Tanggal Kembali",
            "Durasi Sewa",
            "Total Harga"
    };
    riwayatTabelModel.addRow(riwayatHeader);
    cetakBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String pelangganTeks = inputPelanggan.getText();
        String pegawaiTeks = inputAdmin.getText();
        String kodePS = boxKode.getSelectedItem().toString();
        String tipePS = boxTipe.getSelectedItem().toString();
        String tanggalSewa = inputTanggalSewa.getText();
        String tanggalKembali = inputTanggalKembali.getText();
        String durasiStr = inputDurasi.getText();
        String hargaStr = inputHarga.getText();

        if (!pelangganTeks.isEmpty() && !pegawaiTeks.isEmpty() && !kodePS.isEmpty() && !tanggalSewa.isEmpty() && !tanggalKembali.isEmpty() && !durasiStr.isEmpty()) {
          Pelanggan pelanggan = new Pelanggan(pelangganTeks);
          Pegawai pegawai = new Pegawai(pegawaiTeks);
          PS ps = new PS(kodePS,tipePS);

          try {
            int durasi = Integer.parseInt(durasiStr);
            double harga = Double.parseDouble(hargaStr);
            Sewa sewa = new Sewa(pelanggan, pegawai, ps, harga, tanggalSewa, tanggalKembali, durasi);

            DefaultTableModel model = (DefaultTableModel) riwayatTabel.getModel();
            Object[] row = {
                    pelanggan.getNama(),
                    pegawai.getNama(),
                    ps.getTipePS(),
                    tanggalSewa,
                    tanggalKembali,
                    durasi + " hari",
                    "Rp." + sewa.getTotalBiaya()
            };
            model.addRow(row);

            JOptionPane.showMessageDialog(menuPanel, sewa.toString(), "Nota", JOptionPane.INFORMATION_MESSAGE);

            inputPelanggan.setText("");
            inputAdmin.setText("");
            boxKode.setSelectedItem("Kode-1");
            boxTipe.setSelectedItem("PlayStation1");
            inputHarga.setText("");
            inputTanggalSewa.setText("");
            inputTanggalKembali.setText("");
            inputDurasi.setText("");
          }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(menuPanel, "Harga dan Durasi Harus Angka!!");
          }
        }else {
          JOptionPane.showMessageDialog(menuPanel, "Harap isi semua data dengan lengkap!!");
        }

      }
    });
  }

  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        new MainFrame().setVisible(true);
      }
    });
  }
}

