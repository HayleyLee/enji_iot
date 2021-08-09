package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.FileInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface FileInfoService {
    Map<String, Object> selectPageList(PageBean pb, FileInfoBO fileInfo);
    Map<String, Object> selectOne(FileInfoBO fileInfo);
    Map<String, Object> update(FileInfoBO fileInfo);
    Map<String, Object> insert(FileInfoBO fileInfo);
}
