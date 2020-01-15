package cn.infomany;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DELL
 */
@Data
public class UserEntity implements Serializable {
    private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;
}