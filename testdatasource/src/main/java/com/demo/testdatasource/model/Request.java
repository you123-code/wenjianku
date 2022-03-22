package com.demo.testdatasource.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 项目申报信息
 * </p>
 *
 * @author astupidcoder
 * @since 2022-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("RD_REQUEST")
public class Request extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * DTO|VO 唯一ID
     */
    @TableId("ID")
    private String id;

    /**
     * VO 用户ID
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * VO 业务类型ID
     */
    @TableField("PROJECT_ID")
    private String projectId;

    /**
     * VO 业务类型标识
     */
    @TableField("PROJECT_TYPE")
    private String projectType;

    /**
     * DTO|VO 项目备案证名称
     */
    @TableField("PROJECT_NAME")
    private String projectName;

    /**
     * VO 审查批次ID
     */
    @TableField("REVIEW_BATCH_ID")
    private String reviewBatchId;

    /**
     * DTO|VO 审查批次名称
     */
    @TableField("REVIEW_BATCH_NAME")
    private String reviewBatchName;

    /**
     * VO 确认批次ID
     */
    @TableField("CONFIRM_BATCH_ID")
    private String confirmBatchId;

    /**
     * DTO|VO 确认批次名称
     */
    @TableField("CONFIRM_BATCH_NAME")
    private String confirmBatchName;

    /**
     * VO 解保批次ID
     */
    @TableField("RELIEVE_GUARANTEE_BATCH_ID")
    private String relieveGuaranteeBatchId;

    /**
     * DTO|VO 解保批次名称
     */
    @TableField("RELIEVE_GUARANTEE_BATCH_NAME")
    private String relieveGuaranteeBatchName;

    /**
     * DTO|VO 贷款机构名称
     */
    @TableField("CREDITOR_NAME")
    private String creditorName;

    /**
     * DTO|VO 申请金额（元）
     */
    @TableField("MONEY")
    private BigDecimal money;

    /**
     * DTO|VO 授信金额（元）
     */
    @TableField("CREDIT_MONEY")
    private BigDecimal creditMoney;

    /**
     * DTO|VO 原担保机构名称
     */
    @TableField("ORI_GUARANTEE_AGENCY")
    private String oriGuaranteeAgency;

    /**
     * DTO|VO 经营主体名称
     */
    @TableField("MANAGE_ENTITY_NAME")
    private String manageEntityName;

    /**
     * DTO|VO 经营主体证件号码
     */
    @TableField("MANAGE_ENTITY_NUMBER")
    private String manageEntityNumber;

    /**
     * DTO|VO 是否申报国家融资担保基金项目(Y/N)
     */
    @TableField("IS_NATIONAL_GUARANTEE")
    private String isNationalGuarantee;

    /**
     * DTO|VO 国担基金业务品种
     */
    @TableField("BUSINESS_TYPES")
    private String businessTypes;

    /**
     * DTO|VO 佛山融担基金业务品种
     */
    @TableField("BUSINESS_TYPES_FS")
    private String businessTypesFs;

    /**
     * DTO|VO 融资用途
     */
    @TableField("FINANCING_PURPOSE")
    private String financingPurpose;

    /**
     * DTO|VO 上报融担基金批次
     */
    @TableField("REPORT_FINANCIAL_BATCH")
    private String reportFinancialBatch;

    /**
     * DTO|VO 借款人名称
     */
    @TableField("DEBTOR_NAME")
    private String debtorName;

    /**
     * DTO|VO 借款人性质
     */
    @TableField("DEBTOR_NATURE")
    private String debtorNature;

    /**
     * DTO|VO 借款人证件类型
     */
    @TableField("DEBTOR_ID_TYPE")
    private String debtorIdType;

    /**
     * DTO|VO 借款人证件号码
     */
    @TableField("DEBTOR_ID_NUMBER")
    private String debtorIdNumber;

    /**
     * DTO|VO 申请期限（月）
     */
    @TableField("DEADLINE")
    private String deadline;

    /**
     * DTO|VO 授信期限（月）
     */
    @TableField("CREDIT_TERM")
    private String creditTerm;

    /**
     * DTO|VO 是否首次贷款(Y/N)
     */
    @TableField("IS_FIRST_LOAN")
    private String isFirstLoan;

    /**
     * DTO|VO 借据号
     */
    @TableField("IOU_NO")
    private String iouNo;

    /**
     * DTO|VO 登记所在地
     */
    @TableField("REGISTER_LOCATION")
    private String registerLocation;

    /**
     * DTO|VO 所属行业
     */
    @TableField("INDUSTRY_COMMERCE")
    private String industryCommerce;

    /**
     * DTO|VO 从业人数（上一年末）
     */
    @TableField("EMPLOYEES_COUNT")
    private String employeesCount;

    /**
     * DTO|VO 上一年末资产总额（万元）
     */
    @TableField("ASSETS")
    private BigDecimal assets;

    /**
     * DTO|VO 上一年度营业收入（万元）
     */
    @TableField("INCOME")
    private BigDecimal income;

    /**
     * DTO|VO 缴纳税收（元）
     */
    @TableField("PAY_TAXES")
    private BigDecimal payTaxes;

    /**
     * DTO|VO 企业划型
     */
    @TableField("ENTERPRISE_PLANNING")
    private String enterprisePlanning;

    /**
     * DTO|VO 是否高新技术企业(Y/N)
     */
    @TableField("IS_HIGH_TECH")
    private String isHighTech;

    /**
     * DTO|VO 是否到期自动释放额度(Y/N)
     */
    @TableField("IS_EXPIRE_AUTO_RELEASE")
    private String isExpireAutoRelease;

    /**
     * DTO|VO 政策扶持领域类别（服务对象类型）
     */
    @TableField("FIELD_CATEGORY")
    private String fieldCategory;

    /**
     * DTO|VO 房产抵押(Y/N)
     */
    @TableField("GUARANTEE_REAL_ESTATE")
    private String guaranteeRealEstate;

    /**
     * DTO|VO 存单质押担保担保(Y/N)
     */
    @TableField("GUARANTEE_DEPOSIT")
    private String guaranteeDeposit;

    /**
     * DTO|VO 保证担保(Y/N)
     */
    @TableField("GUARANTEE_ENSURE")
    private String guaranteeEnsure;

    /**
     * DTO|VO 其他担保(Y/N)
     */
    @TableField("GUARANTEE_OTHER")
    private String guaranteeOther;

    /**
     * DTO|VO 分（支）行
     */
    @TableField("BANK_BREACH")
    private String bankBreach;

    /**
     * DTO|VO 主债权金额（元）
     */
    @TableField("PRI_CLAIM_MONEY")
    private BigDecimal priClaimMoney;

    /**
     * DTO|VO 债权起始日期
     */
    @TableField("PRI_CLAIM_START_DATE")
    private Long priClaimStartDate;

    /**
     * DTO|VO 债权到期日期
     */
    @TableField("PRI_CLAIM_MATURITY_DATE")
    private Long priClaimMaturityDate;

    /**
     * DTO|VO 额度释放日期
     */
    @TableField("RELEASE_DATE")
    private Long releaseDate;

    /**
     * DTO|VO 担保金额（元）
     */
    @TableField("GUARANTEE_MONEY")
    private BigDecimal guaranteeMoney;

    /**
     * DTO|VO 借款合同号
     */
    @TableField("LOAN_CONTRACT_NO")
    private String loanContractNo;

    /**
     * DTO|VO 贷款期限（月）
     */
    @TableField("LOAN_TERM")
    private String loanTerm;

    /**
     * DTO|VO 担保期限（月）
     */
    @TableField("GUARANTEE_TERM")
    private String guaranteeTerm;

    /**
     * DTO|VO 贷款利率（年）（%）
     */
    @TableField("LOAN_INTEREST_RATE")
    private BigDecimal loanInterestRate;

    /**
     * DTO|VO 保证合同号
     */
    @TableField("GUARANTEE_CONTRACT_NO")
    private String guaranteeContractNo;

    /**
     * DTO|VO 委保合同号
     */
    @TableField("ENTRUSTED_CONTRACT_NO")
    private String entrustedContractNo;

    /**
     * DTO|VO 担保费率（年）（%）
     */
    @TableField("GUARANTEE_RATE")
    private BigDecimal guaranteeRate;

    /**
     * DTO|VO 原应收取担保费率（年）（%）
     */
    @TableField("ORI_GUARANTEE_RATE")
    private BigDecimal oriGuaranteeRate;

    /**
     * DTO|VO 反担保措施
     */
    @TableField("ANTI_GUARANTEE_MEASURES")
    private String antiGuaranteeMeasures;

    /**
     * DTO|VO 解保类型（正常解保/代偿解保）
     */
    @TableField("RELIEVE_TYPE")
    private String relieveType;

    /**
     * DTO|VO 再担保机构名称
     */
    @TableField("GUARANTEE_AGENCY")
    private String guaranteeAgency;

    /**
     * DTO|VO 分险比例（合作贷款机构）
     */
    @TableField("RISK_RATIO_CREDITOR")
    private BigDecimal riskRatioCreditor;

    /**
     * DTO|VO 分险比例（原担保）
     */
    @TableField("RISK_RATIO_ORI_GUARANTEE")
    private BigDecimal riskRatioOriGuarantee;

    /**
     * DTO|VO 分险比例（再担保）
     */
    @TableField("RISK_RATIO_RE_GUARANTEE")
    private BigDecimal riskRatioReGuarantee;

    /**
     * DTO|VO 分险比例（佛山市融资担保基金）
     */
    @TableField("RISK_RATIO_FS_GUARANTEE")
    private BigDecimal riskRatioFsGuarantee;

    /**
     * DTO|VO 分险比例其他
     */
    @TableField("RISK_RATIO_ELSE")
    private BigDecimal riskRatioElse;

    /**
     * DTO|VO 实例ID
     */
    @TableField("INSTANCE_ID")
    private String instanceId;

    /**
     * DTO|VO 涉诉备注
     */
    @TableField("PROSECUTE_REMARK")
    private String prosecuteRemark;

    /**
     * DTO|VO 其他备注
     */
    @TableField("REMARK")
    private String remark;

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
     * VO 修改人ID
     */
    @TableField("UPDATED_USER_ID")
    private String updatedUserId;

    /**
     * VO 修改人姓名
     */
    @TableField("UPDATED_USER_NAME")
    private String updatedUserName;

    /**
     * DTO|VO 额度冻结时间
     */
    @TableField("QUOTA_FREEZE_TIME")
    private Long quotaFreezeTime;

    /**
     * DTO|VO 时点余额
     */
    @TableField("TIME_BALANCE")
    private BigDecimal timeBalance;

    /**
     * DTO|VO 组织id
     */
    @TableField("ORG_ID")
    private String orgId;

    /**
     * DTO|VO 组织名称
     */
    @TableField("ORG_NAME")
    private String orgName;

    /**
     * DTO|VO 担保方式(001抵押 002质押 003保证 004信用 005综合担保)
     */
    @TableField("GUARANTEE_WAY")
    private String guaranteeWay;

    /**
     * DTO|VO 是否到期自动释放额度
     */
    @TableField("IS_AUTO_RELEASE")
    private String isAutoRelease;

    /**
     * DTO|VO 产品ID
     */
    @TableField("PRODUCT_ID")
    private String productId;

    /**
     * DTO|VO 产品名称
     */
    @TableField("PRODUCT_NAME")
    private String productName;

    /**
     * DTO|VO 产品类型
     */
    @TableField("PRODUCT_TYPE")
    private String productType;

    /**
     * DTO|VO 备案证ID
     */
    @TableField("PROJECT_RECORD_ID")
    private String projectRecordId;

    /**
     * DTO|VO 业务编码
     */
    @TableField("APPLICATION_NUMBER")
    private String applicationNumber;

    /**
     * DTO|VO 债款编号
     */
    @TableField("DEBT_NO")
    private String debtNo;

    /**
     * DTO|VO 还款方式
     */
    @TableField("PAY_WAY")
    private String payWay;

    /**
     * DTO|VO 产品编号
     */
    @TableField("PRODUCT_NO")
    private String productNo;

    /**
     * DTO|VO 贷款机构
     */
    @TableField("LENDING_AGENCY")
    private String lendingAgency;

    /**
     * DTO|VO 申请人
     */
    @TableField("CONTACT_PERSON")
    private String contactPerson;

    /**
     * DTO|VO 申请人联系电话
     */
    @TableField("CONTACT_PHONE")
    private String contactPhone;

    /**
     * DTO|VO 产品负责人电话
     */
    @TableField("PRODUCT_CONTACT_PHONE")
    private String productContactPhone;

    /**
     * DTO|VO 产品联系人
     */
    @TableField("PRODUCT_CONTACT_PERSON")
    private String productContactPerson;

    /**
     * DTO|VO 驳回原因
     */
    @TableField("REJECT_REASON")
    private String rejectReason;

    /**
     * DTO|VO 业务类型ID
     */
    @TableField("BUSINESS_TYPE_ID")
    private String businessTypeId;

    /**
     * DTO|VO 业务报送提交时间
     */
    @TableField("BUSINESS_SUMBIT_AT")
    private Long businessSumbitAt;

    /**
     * DTO|VO 数据来源类型（1线下导入,2线上申请）
     */
    @TableField("DATA_ORIGIN_TYPE")
    private String dataOriginType;

    /**
     * DTO|VO 是否信用贷款（Y/N）
     */
    @TableField("IS_CREDIT_LOAN")
    private String isCreditLoan;

    /**
     * DTO|VO 担保合同金额(万元)
     */
    @TableField("GUARANTEE_CONTRACT_AMOUNT")
    private BigDecimal guaranteeContractAmount;

    /**
     * DTO|VO 贷款余额(万元)
     */
    @TableField("LOAN_BALANCE")
    private BigDecimal loanBalance;

    /**
     * DTO|VO 担保余额(万元)
     */
    @TableField("GUARANTEED_BALANCE")
    private BigDecimal guaranteedBalance;

    /**
     * DTO|VO 业务报送自动校验失败原因
     */
    @TableField("CHECK_FAIL_REASON")
    private String checkFailReason;

    /**
     * DTO|VO 是否为共同借款人
     */
    @TableField("IS_COMMON_BORROWER")
    private String isCommonBorrower;


}
