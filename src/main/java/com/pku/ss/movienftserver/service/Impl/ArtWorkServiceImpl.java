package com.pku.ss.movienftserver.service.Impl;

import com.pku.ss.movienftserver.dao.ArtworkInfoStorage;
import com.pku.ss.movienftserver.data.Artwork;
import com.pku.ss.movienftserver.data.Page;
import com.pku.ss.movienftserver.service.ArtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArtWorkServiceImpl  implements ArtWorkService {

    @Autowired
    ArtworkInfoStorage artworkInfoStorage;

    @Override
    public Page<Artwork> batchGetArtworks(int currentPage, int pageSize) {
        Page<Artwork> page = new Page<>();
        page.setTotalNum(artworkInfoStorage.getArtWorkCount());
        page.setPageCount((page.getTotalNum()%pageSize==0?0:1) + page.getTotalNum()/pageSize);
        page.setPageInfo(artworkInfoStorage.batchGetArtworkInfo(currentPage , pageSize));
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        return page;
    }
}
