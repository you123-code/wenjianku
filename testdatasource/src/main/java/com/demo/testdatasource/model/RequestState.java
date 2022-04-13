package com.demo.testdatasource.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 业务表状态
 * </p>
 *
 * @author youwei
 * @since 2022-04-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("RD_REQUEST_STATE")
public class RequestState extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * DTO|VO 唯一ID,与业务数据id相同
     */
    @TableId("ID")
    private String id;

    /**
     * DTO|VO 业务确认(Y/N)
     */
    @TableField("CONFIRM_STATE")
    private String confirmState;

    /**
     * DTO|VO 意见状态 Y=已填写,N=未填写
     */
    @TableField("OPINION_STATE")
    private String opinionState;

    /**
     * DTO|VO 校验状态 abnormal=异常,normal=正常,not=没校验
     */
    @TableField("CHECK_STATE")
    private String checkState;

    /**
     * DTO|VO 是否完善 N 待完善|Y 已完善|R 已驳回
     */
    @TableField("IS_COMPLETE")
    private String isComplete;

    /**
     * 已处理的最新节点(页面标签)
     */
    @TableField("NEWEST_NODE")
    private String newestNode;

    /**
     * DTO|VO 是否已通过形式审查
     */
    @TableField("IF_PASS_FORM_CHECK")
    private String ifPassFormCheck;

    /**
     * DTO|VO 是否为银行对接数据
     */
    @TableField("IS_BANK_DATA")
    private String isBankData;

    /**
     * DTO|VO 状态
     */
    @TableField("STATUS")
    private String status;

    /**
     * VO 创建时间
     */
    @TableField("CREATED_AT")
    private Long createdAt;

    /**
     * VO 更新时间
     */
    @TableField("UPDATED_AT")
    private Long updatedAt;

    /**
     * DTO|VO 形式审查(new=未审批,no=不同意,done=同意,ing=进行中)
     */
    @TableField("FORM_CHECK")
    private String formCheck;

    /**
     * DTO|VO 登记备案(new=未审批,no=不同意,done=同意,ing=进行中)
     */
    @TableField("REGISTER_RECORD")
    private String registerRecord;

    /**
     * DTO|VO 业务确认(new=未审批,no=不同意,done=同意,ing=进行中)
     */
    @TableField("WORK_CONFIRM")
    private String workConfirm;

    /**
     * DTO|VO 业务解保(new=未审批,no=不同意,done=同意,ing=进行中)
     */
    @TableField("RELIEVE_GUARANTEE")
    private String relieveGuarantee;

    /**
     * DTO|VO 贷款状态
     */
    @TableField("LOAN_STATUS")
    private String loanStatus;

    /**
     * DTO|VO 还款计划状态 Y(已导入) N(未导入)
     */
    @TableField("REPAYMENT_PLAN_STATUS")
    private String repaymentPlanStatus;

    /**
     * DTO|VO 是否处于草稿状态(Y/N)
     */
    @TableField("IS_CACHE")
    private String isCache;

    /**
     * 废弃字段
     */
    @TableField("ABANDONED_IS_LENDING_CACHE")
    private String abandonedIsLendingCache;

    /**
     * 废弃字段
     */
    @TableField("ABANDONED_IS_FIRST_LENDING")
    private String abandonedIsFirstLending;

    /**
     * VO 意向同步状态(success成功,running同步中,fail失败)
     */
    @TableField("IS_SYNC_INTENTION")
    private String isSyncIntention;

    /**
     * VO 形式审查结果同步状态(success成功,running同步中,fail失败)
     */
    @TableField("IS_SYNC_CHECK")
    private String isSyncCheck;

    /**
     * VO 业务确认结果同步状态(success成功,running同步中,fail失败)
     */
    @TableField("IS_SYNC_CONFIRM")
    private String isSyncConfirm;

    /**
     * DTO|VO 业务报送草稿校验状态 N=校验未通过,Y=校验通过
     */
    @TableField("CACHE_CHECK_STATE")
    private String cacheCheckState;

    /**
     * DTO|VO 是否为银行对接数据
     */
    @TableField("IS_FIX_DATA")
    private String isFixData;


}
