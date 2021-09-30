package com.pku.ss.movienftserver.service.Impl;

import com.pku.ss.movienftserver.dao.CopyrightInfoStorage;
import com.pku.ss.movienftserver.data.Page;
import com.pku.ss.movienftserver.data.enums.Copyright;
import com.pku.ss.movienftserver.service.CopyrightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class CopyrightServiceImpl implements CopyrightService {

    @Autowired
    CopyrightInfoStorage copyrightInfoStorage;

    @Override
    public Page<Copyright> batchGetCopyright(int currentPage, int pageSize) {
        Page<Copyright> page = new Page<>();
        page.setTotalNum(copyrightInfoStorage.getCopyrightCount());
        page.setPageCount((page.getTotalNum()%pageSize==0?0:1) + page.getTotalNum()/pageSize);
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setPageInfo(copyrightInfoStorage.batchGetCopyrightInfo(currentPage , pageSize));
        return page;
    }
}
