package com.atguigu.ggkt.vod.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视频来访者记录表
 * </p>
 *
 * @author atguigu
 * @since 2022-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VideoVisitor implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 视频id
     */
    private Long videoId;

    /**
     * 来访者用户id
     */
    private String userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 进入时间
     */
    private String joinTime;

    /**
     * 离开的时间
     */
    private String leaveTime;

    /**
     * 用户停留的时间(单位：秒)
     */
    private Long duration;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDeleted;


}
