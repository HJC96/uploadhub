package com.UploadHub.uploadhub.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


/*데이베이스의 거의 모든 테이블에는 데이터가 추가된 시간이나 수정 된 시간등이 칼럼으로 작성된다. 자바에서는 이를 쉽게 처리하고 @MappedSuperClass를 이용해서 공통으로 사용되는 칼럼들을 지정하고, 해당 클래스를 상속해서 이를 처리한다.*/

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {

    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="moddate")
    private LocalDateTime modDate;

}
