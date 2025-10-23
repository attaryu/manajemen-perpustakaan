package com.manajemen.perpustakaan;

import javax.swing.JFrame;

import com.manajemen.perpustakaan.controller.BukuController;
import com.manajemen.perpustakaan.controller.TransaksiPeminjamanController;
import com.manajemen.perpustakaan.repository.BukuRepository;
import com.manajemen.perpustakaan.repository.EksemplarBukuRepository;
import com.manajemen.perpustakaan.repository.MahasiswaRepository;
import com.manajemen.perpustakaan.repository.TransaksiPeminjamanRepository;
import com.manajemen.perpustakaan.view.BukuView;
import com.manajemen.perpustakaan.view.TambahBukuView;
import com.manajemen.perpustakaan.view.TambahPeminjamanView;
import com.manajemen.perpustakaan.view.TransaksiPeminjamanView;
import com.manajemen.perpustakaan.view.UpdateBukuView;
import com.manajemen.perpustakaan.view.UpdatePeminjamanView;

public class MainController {
  MahasiswaRepository mahasiswaRepo;
  BukuRepository bukuRepo;
  EksemplarBukuRepository eksemplarBukuRepo;
  TransaksiPeminjamanRepository transaksiPeminjamanRepo;

  // controller
  TransaksiPeminjamanController transaksiPeminjamanController;
  BukuController bukuController;

  // main view
  MainView mainView;

  // transaksi view
  TransaksiPeminjamanView transaksiPeminjamanView;
  TambahPeminjamanView tambahPeminjamanView;
  UpdatePeminjamanView updatePeminjamanView;

  // buku view
  BukuView bukuView;
  TambahBukuView tambahBukuView;
  UpdateBukuView updateBukuView;

  public MainController() {
    this.initRepo();
    this.initView();
    this.initController();
    this.setupMainView();
  }

  private void initRepo() {
    this.mahasiswaRepo = new MahasiswaRepository();
    this.bukuRepo = new BukuRepository();
    this.eksemplarBukuRepo = new EksemplarBukuRepository();
    this.transaksiPeminjamanRepo = new TransaksiPeminjamanRepository();
  }

  private void initView() {
    this.transaksiPeminjamanView = new TransaksiPeminjamanView();
    this.tambahPeminjamanView = new TambahPeminjamanView();
    this.updatePeminjamanView = new UpdatePeminjamanView();

    this.bukuView = new BukuView();
    this.tambahBukuView = new TambahBukuView();
    this.updateBukuView = new UpdateBukuView();

    this.transaksiPeminjamanView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.bukuView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

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
        this::navigateToMain);
  }

  private void setupMainView() {
    this.mainView = new MainView();

    this.mainView.getTransaksiButton().addActionListener((_) -> this.navigateToTransaksi());

    this.mainView.getBukuButton().addActionListener((_) -> this.navigateToBuku());
  }

  private void navigateToTransaksi() {
    this.hideAllViews();
    this.transaksiPeminjamanController.index();
  }

  private void navigateToBuku() {
    this.hideAllViews();
    this.bukuController.index();
  }

  private void navigateToMain() {
    this.hideAllViews();
    this.mainView.setVisible(true);
  }

  private void hideAllViews() {
    this.bukuView.setVisible(false);
    this.transaksiPeminjamanView.setVisible(false);
    this.mainView.setVisible(false);
  }

  public void start() {
    this.mainView.setVisible(true);
  }
}
