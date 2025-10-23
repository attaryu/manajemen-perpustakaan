package com.manajemen.perpustakaan;

import javax.swing.JFrame;

import com.manajemen.perpustakaan.controller.BukuController;
import com.manajemen.perpustakaan.controller.EksemplarController;
import com.manajemen.perpustakaan.controller.TransaksiPeminjamanController;
import com.manajemen.perpustakaan.repository.BukuRepository;
import com.manajemen.perpustakaan.repository.EksemplarBukuRepository;
import com.manajemen.perpustakaan.repository.MahasiswaRepository;
import com.manajemen.perpustakaan.repository.TransaksiPeminjamanRepository;
import com.manajemen.perpustakaan.view.BukuView;
import com.manajemen.perpustakaan.view.EksemplarView;
import com.manajemen.perpustakaan.view.TambahBukuView;
import com.manajemen.perpustakaan.view.TambahPeminjamanView;
import com.manajemen.perpustakaan.view.TransaksiPeminjamanView;
import com.manajemen.perpustakaan.view.UpdateBukuView;
import com.manajemen.perpustakaan.view.UpdatePeminjamanView;

/**
 * Controller utama yang mengatur navigasi dan koordinasi antar modul dalam aplikasi.
 * Kelas ini mengelola semua repository, controller, dan view yang ada di aplikasi,
 * serta mengatur routing antar halaman.
 * 
 * @author attaryu
 * @version 1.0
 * @since 2025-10-23
 */
public class MainController {
  /** Repository untuk mengelola data mahasiswa */
  MahasiswaRepository mahasiswaRepo;
  
  /** Repository untuk mengelola data buku */
  BukuRepository bukuRepo;
  
  /** Repository untuk mengelola data eksemplar buku */
  EksemplarBukuRepository eksemplarBukuRepo;
  
  /** Repository untuk mengelola data transaksi peminjaman */
  TransaksiPeminjamanRepository transaksiPeminjamanRepo;

  /** Controller untuk mengelola transaksi peminjaman */
  TransaksiPeminjamanController transaksiPeminjamanController;
  
  /** Controller untuk mengelola data buku */
  BukuController bukuController;
  
  /** Controller untuk mengelola eksemplar buku */
  EksemplarController eksemplarController;

  /** View utama aplikasi */
  MainView mainView;

  /** View untuk menampilkan daftar transaksi peminjaman */
  TransaksiPeminjamanView transaksiPeminjamanView;
  
  /** View untuk menambah transaksi peminjaman */
  TambahPeminjamanView tambahPeminjamanView;
  
  /** View untuk mengubah transaksi peminjaman */
  UpdatePeminjamanView updatePeminjamanView;

  /** View untuk menampilkan daftar buku */
  BukuView bukuView;
  
  /** View untuk menambah buku */
  TambahBukuView tambahBukuView;
  
  /** View untuk mengubah data buku */
  UpdateBukuView updateBukuView;

  /** View untuk menampilkan daftar eksemplar buku */
  EksemplarView eksemplarView;

  /**
   * Konstruktor untuk membuat instance MainController.
   * Melakukan inisialisasi repository, view, controller, dan setup view utama.
   */
  public MainController() {
    this.initRepo();
    this.initView();
    this.initController();
    this.setupMainView();
  }

  /**
   * Melakukan inisialisasi semua repository yang dibutuhkan aplikasi.
   */
  private void initRepo() {
    this.mahasiswaRepo = new MahasiswaRepository();
    this.bukuRepo = new BukuRepository();
    this.eksemplarBukuRepo = new EksemplarBukuRepository();
    this.transaksiPeminjamanRepo = new TransaksiPeminjamanRepository();
  }

  /**
   * Melakukan inisialisasi semua view yang dibutuhkan aplikasi.
   * Mengatur default close operation untuk beberapa view.
   */
  private void initView() {
    this.transaksiPeminjamanView = new TransaksiPeminjamanView();
    this.tambahPeminjamanView = new TambahPeminjamanView();
    this.updatePeminjamanView = new UpdatePeminjamanView();

    this.bukuView = new BukuView();
    this.tambahBukuView = new TambahBukuView();
    this.updateBukuView = new UpdateBukuView();

    this.eksemplarView = new EksemplarView();

    this.transaksiPeminjamanView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.bukuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.eksemplarView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Melakukan inisialisasi semua controller dengan dependency yang dibutuhkan.
   */
  private void initController() {
    this.transaksiPeminjamanController = new TransaksiPeminjamanController(
        this.transaksiPeminjamanView,
        this.tambahPeminjamanView,
        this.updatePeminjamanView,
        this.bukuRepo,
        this.mahasiswaRepo,
        this.eksemplarBukuRepo,
        this.transaksiPeminjamanRepo,
        this::navigateToMain);

    this.bukuController = new BukuController(
        this.bukuRepo,
        this.eksemplarBukuRepo,
        this.bukuView,
        this.tambahBukuView,
        this.updateBukuView,
        this::navigateToMain,
        this::navigateToEksemplar);

    this.eksemplarController = new EksemplarController(
        this.eksemplarView,
        this.bukuRepo,
        this.eksemplarBukuRepo,
        this::navigateToBuku);
  }

  /**
   * Melakukan setup view utama dan menghubungkan action listener untuk tombol navigasi.
   */
  private void setupMainView() {
    this.mainView = new MainView();

    this.mainView.getTransaksiButton().addActionListener((_) -> this.navigateToTransaksi());
    this.mainView.getBukuButton().addActionListener((_) -> this.navigateToBuku());
  }

  /**
   * Navigasi ke halaman transaksi peminjaman.
   * Menyembunyikan semua view dan menampilkan view transaksi.
   */
  private void navigateToTransaksi() {
    this.hideAllViews();
    this.transaksiPeminjamanController.index();
  }

  /**
   * Navigasi ke halaman daftar buku.
   * Menyembunyikan semua view dan menampilkan view buku.
   */
  private void navigateToBuku() {
    this.hideAllViews();
    this.bukuController.index();
  }

  /**
   * Navigasi ke halaman daftar eksemplar buku berdasarkan ISBN.
   * Menyembunyikan semua view dan menampilkan view eksemplar untuk buku tertentu.
   * 
   * @param isbn ISBN buku yang akan ditampilkan eksemplarnya
   */
  private void navigateToEksemplar(String isbn) {
    this.hideAllViews();
    this.eksemplarController.index(isbn);
  }

  /**
   * Navigasi kembali ke halaman utama.
   * Menyembunyikan semua view dan menampilkan view utama.
   */
  private void navigateToMain() {
    this.hideAllViews();
    this.mainView.setVisible(true);
  }

  /**
   * Menyembunyikan semua view yang sedang ditampilkan.
   * Method ini digunakan sebelum menampilkan view baru untuk memastikan hanya satu view yang aktif.
   */
  private void hideAllViews() {
    this.bukuView.setVisible(false);
    this.transaksiPeminjamanView.setVisible(false);
    this.mainView.setVisible(false);
  }

  /**
   * Memulai aplikasi dengan menampilkan view utama.
   */
  public void start() {
    this.mainView.setVisible(true);
  }
}
