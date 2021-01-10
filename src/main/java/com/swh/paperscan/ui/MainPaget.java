/*
 * Copyright (c) 2021. Song Wenhao.
 * All rights reserved.
 */

package com.swh.paperscan.ui;

import com.swh.paperscan.pojo.ScanRes;
import com.swh.paperscan.service.ExcelSvc;
import com.swh.paperscan.service.PaperScanSvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class MainPaget {
    private JPanel rootPanel;
    private JButton btnChoose;
    private JButton btnSave;
    private JButton btnAction;
    private JLabel scanPath;
    private JLabel savePath;
    private JLabel msg;


    public MainPaget() {
        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser("选择扫描目录");
                jFileChooser.setApproveButtonText("确定");
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jFileChooser.showOpenDialog(new JLabel());
                File file = jFileChooser.getSelectedFile();
                scanPath.setText(file.getPath());
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser("选择EXCEL导出目录");
                jFileChooser.setApproveButtonText("确定");
                jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jFileChooser.showOpenDialog(new JLabel());
                File file = jFileChooser.getSelectedFile();
                savePath.setText(file.getPath());
            }
        });
        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scanPath.getText() == "") {
                    msg.setText("请选择扫描目录");
                    return;
                }
                if (savePath.getText() == "") {
                    msg.setText("请选择保存目录");
                    return;
                }
                msg.setText("扫描中...");
                PaperScanSvc paperScanSvc = new PaperScanSvc();
                List<ScanRes> resList = paperScanSvc.doScan(scanPath.getText());
                msg.setText("扫描完成！");
                msg.setText("生成EXCEL中...");
                ExcelSvc excelSvc = new ExcelSvc();
                String res = excelSvc.doExport(savePath.getText(), resList);
                if (res == "") {
                    msg.setText("操作失败，请重试！");
                }
                msg.setText("操作完成，保存位置：" + res);

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainPaget");
        frame.setContentPane(new MainPaget().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
