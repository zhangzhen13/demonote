package com.example.demonote.domain;//实体层domain


import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class note {
    private Integer id;
    private String name;
    private String content;
    @TableLogic
    private Integer isDelete;
    private Integer uId;
}

