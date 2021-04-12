package com.hml.atp.zues.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * card_bin
 * @author 
 */
@Data
public class CardBin implements Serializable {
    private Integer id;

    /**
     * 发卡行名称
     */
    private String issuingBankName;

    /**
     * Organization code 机构代码
     */
    private Integer orgCode;

    /**
     * 卡名
     */
    private String cardName;

    /**
     * 卡号长度
     */
    private Integer codeLength;

    /**
     * 卡BIN长度
     */
    private Integer cardBinLength;

    /**
     * 卡BIN
     */
    private Integer cardBin;

    /**
     * 卡种
     */
    private String cardType;

    private static final long serialVersionUID = 1L;



    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CardBin other = (CardBin) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIssuingBankName() == null ? other.getIssuingBankName() == null : this.getIssuingBankName().equals(other.getIssuingBankName()))
            && (this.getOrgCode() == null ? other.getOrgCode() == null : this.getOrgCode().equals(other.getOrgCode()))
            && (this.getCardName() == null ? other.getCardName() == null : this.getCardName().equals(other.getCardName()))
            && (this.getCodeLength() == null ? other.getCodeLength() == null : this.getCodeLength().equals(other.getCodeLength()))
            && (this.getCardBinLength() == null ? other.getCardBinLength() == null : this.getCardBinLength().equals(other.getCardBinLength()))
            && (this.getCardBin() == null ? other.getCardBin() == null : this.getCardBin().equals(other.getCardBin()))
            && (this.getCardType() == null ? other.getCardType() == null : this.getCardType().equals(other.getCardType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssuingBankName() == null) ? 0 : getIssuingBankName().hashCode());
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getCardName() == null) ? 0 : getCardName().hashCode());
        result = prime * result + ((getCodeLength() == null) ? 0 : getCodeLength().hashCode());
        result = prime * result + ((getCardBinLength() == null) ? 0 : getCardBinLength().hashCode());
        result = prime * result + ((getCardBin() == null) ? 0 : getCardBin().hashCode());
        result = prime * result + ((getCardType() == null) ? 0 : getCardType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", issuingBankName=").append(issuingBankName);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", cardName=").append(cardName);
        sb.append(", codeLength=").append(codeLength);
        sb.append(", cardBinLength=").append(cardBinLength);
        sb.append(", cardBin=").append(cardBin);
        sb.append(", cardType=").append(cardType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}