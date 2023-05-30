package com.demo.testdatasource.model;

import lombok.Data;

import java.util.Date;

/**
 * @author youwei
 * @version 1.0
 * @date 2023/4/22 20:52
 */
@Data
public class Merchant {

    private String mchntId;
    private String nxyMchntId;
    private String orgId;
    private String mchntNo;
    private String mchntType;
    private String mchntName;
    private String legalPerson;
    private String legalPersonId;
    private String license;
    private String registeredCapital;
    private String registDt;
    private String expireDt;
    private String businessDesc;
    private String registAddress;
    private String provCd;
    private String cityCd;
    private String countyCd;
    private String busiAddress;
    private String certType;
    private String certNo;
    private String certCorrect;
    private String certOpposite;
    private String certMeet;
    private String cardCorrect;
    private String cardOpposite;
    private String mchntSt;
    private String ownerId;
    private Date rowCrtTs;
    private String rowCrtUsr;
    private Date rowUpdTs;
    private String rowUpdUsr;
    private Integer transSt;
    private String zhiOrgName;
    private String fenOrgName;
    private String contPhone;
    private String id;
    private String mcc;
    private String nature;
    private String contacts;
    private String contAddr;
    private String authRes;
    private Integer syncState;
    private Integer syncCount;
    private Date syncTime;
    private String fileName;
    private String refuseRes;
    private String notifyChannel;
    private String isNewMer;
    private String simpMchntName;
    private String startTime;
    private String endTime;
    private String orgName;
    private String showAddAppUser;
    private String parentOrgName;
    private String orgPath;
    private boolean isNetPos;
    private boolean isCommonweal;
    private String channelFlagStr;
    private String rowCrtUsrQueryParam;
    private String outMchntId;
    private Integer channelFlag;
    private boolean isInclude;
    private String unionpayMixContent;
    private String provNmCn;
    private String regionNmCn;
    private String countyNmCn;
    private String flagOrder;
    private Integer flagCashier;
    private String qrcodeStateStr;
    private String fenOrgId;
    private String blueState;
    private String blueMsg;
    private String posMerNo;
    private String codes;
    private boolean isUnionPayMchnt;
    private boolean undoTag;
    private boolean refundTag;
    private boolean stopCreditCardTag;
    private String cardNo;
    private String[] notShowChannel;
    private String aliMerlevel;
    private Integer aliMerSt;
    private String aliSubMchntId;
    private String wechatChannel;
    private String alipayChannel;
    private boolean wechatExtCfg;
    private boolean alipayExtCfg;
}
