package com.hml.atp.zues.service.impl;

import com.hml.atp.zues.CaseBase;
import com.hml.atp.zues.config.SftpProperties;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.mockito.Mockito.*;

public class FileSystemServiceImplTest extends CaseBase {
    @Mock
    SftpProperties config;
    @InjectMocks
    FileSystemServiceImpl fileSystemServiceImpl;


    @Test
    public void testUploadFile() throws Exception {
        when(config.getHost()).thenReturn("getHostResponse");
        when(config.getPort()).thenReturn(Integer.valueOf(0));
        when(config.getProtocol()).thenReturn("getProtocolResponse");
        when(config.getUsername()).thenReturn("getUsernameResponse");
        when(config.getPassword()).thenReturn("getPasswordResponse");
        when(config.getRoot()).thenReturn("getRootResponse");
        when(config.getSessionStrictHostKeyChecking()).thenReturn("getSessionStrictHostKeyCheckingResponse");
        when(config.getSessionConnectTimeout()).thenReturn(Integer.valueOf(0));
        when(config.getChannelConnectedTimeout()).thenReturn(Integer.valueOf(0));

        boolean result = fileSystemServiceImpl.uploadFile("targetPath", new File("test"));
        Assert.assertEquals(result, true);
    }

    @Test
    public void testUploadFile2() throws Exception {
        when(config.getHost()).thenReturn("getHostResponse");
        when(config.getPort()).thenReturn(Integer.valueOf(0));
        when(config.getProtocol()).thenReturn("getProtocolResponse");
        when(config.getUsername()).thenReturn("getUsernameResponse");
        when(config.getPassword()).thenReturn("getPasswordResponse");
        when(config.getRoot()).thenReturn("getRootResponse");
        when(config.getSessionStrictHostKeyChecking()).thenReturn("getSessionStrictHostKeyCheckingResponse");
        when(config.getSessionConnectTimeout()).thenReturn(Integer.valueOf(0));
        when(config.getChannelConnectedTimeout()).thenReturn(Integer.valueOf(0));

        boolean result = fileSystemServiceImpl.uploadFile("targetPath", new File(getClass().getResource("/com/hml/atp/zues/service/impl/PleaseReplaceMeWithTestFile.txt").getFile()));
        Assert.assertEquals(result, true);
    }

    @Test
    public void testDownloadFile() throws Exception {
        when(config.getHost()).thenReturn("getHostResponse");
        when(config.getPort()).thenReturn(Integer.valueOf(0));
        when(config.getProtocol()).thenReturn("getProtocolResponse");
        when(config.getUsername()).thenReturn("getUsernameResponse");
        when(config.getPassword()).thenReturn("getPasswordResponse");
        when(config.getRoot()).thenReturn("getRootResponse");
        when(config.getSessionStrictHostKeyChecking()).thenReturn("getSessionStrictHostKeyCheckingResponse");
        when(config.getSessionConnectTimeout()).thenReturn(Integer.valueOf(0));
        when(config.getChannelConnectedTimeout()).thenReturn(Integer.valueOf(0));

        File result = fileSystemServiceImpl.downloadFile("targetPath");
        Assert.assertEquals(result, new File(getClass().getResource("/com/hml/atp/zues/service/impl/PleaseReplaceMeWithTestFile.txt").getFile()));
    }

    @Test
    public void testDeleteFile() throws Exception {
        when(config.getHost()).thenReturn("getHostResponse");
        when(config.getPort()).thenReturn(Integer.valueOf(0));
        when(config.getProtocol()).thenReturn("getProtocolResponse");
        when(config.getUsername()).thenReturn("getUsernameResponse");
        when(config.getPassword()).thenReturn("getPasswordResponse");
        when(config.getRoot()).thenReturn("getRootResponse");
        when(config.getSessionStrictHostKeyChecking()).thenReturn("getSessionStrictHostKeyCheckingResponse");
        when(config.getSessionConnectTimeout()).thenReturn(Integer.valueOf(0));
        when(config.getChannelConnectedTimeout()).thenReturn(Integer.valueOf(0));

        boolean result = fileSystemServiceImpl.deleteFile("targetPath");
        Assert.assertEquals(result, true);
    }
}

