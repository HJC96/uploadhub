package com.UploadHub.uploadhub.repository;

import com.UploadHub.uploadhub.domain.Board;
import com.UploadHub.uploadhub.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch{
    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
