package com.crazyandcoder.android.module.test;

/**
 * @ClassName: CardHomeItem
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 5:55 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 5:55 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CardHome {

    private String cardName;
    private String cardUrl;


    public CardHome(String cardName, String cardUrl) {
        this.cardName = cardName;
        this.cardUrl = cardUrl;
    }

    public CardHome() {

    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }


}
