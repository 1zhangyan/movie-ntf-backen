package com.pku.ss.movienftserver.service;

import com.pku.ss.movienftserver.data.Artwork;
import com.pku.ss.movienftserver.data.Page;

public interface ArtWorkService {
    Page<Artwork> batchGetArtworks(int currentPage , int pageSize);
}
