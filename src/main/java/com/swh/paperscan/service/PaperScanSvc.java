/*
 * Copyright (c) 2021. Song Wenhao.
 * All rights reserved.
 */

package com.swh.paperscan.service;

import com.swh.paperscan.pojo.ScanRes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PaperScanSvc {

    public List<ScanRes> doScan(String path) {
        List<ScanRes> list = new ArrayList<>();
        File directory = new File(path);
        File[] filelist = directory.listFiles();
        for (int i = 0; i < filelist.length; i++) {
            /**如果当前是文件夹，进入递归扫描文件夹**/
            if (filelist[i].isDirectory()) {
                /**递归扫描下面的文件夹**/
                List<ScanRes> chr = doScan(filelist[i].getAbsolutePath());
                list.addAll(chr);
            }
            /**非文件夹**/
            else {
                ScanRes scanRes = new ScanRes();
                scanRes.setFilePath(filelist[i].getAbsolutePath());
                scanRes.setFileName(filelist[i].getName());
                list.add(scanRes);
            }
        }
        return list;
    }
}
