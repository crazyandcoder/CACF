package com.crazyandcoder.android.module.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HomeData
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 6:00 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 6:00 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HomeDataUtils {

    private static final String[] images = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583817453&di=dd959ad7a8fec90be92118b274f77664&imgtype=0&src=http%3A%2F%2Fmedia-cdn.tripadvisor.com%2Fmedia%2Fphoto-s%2F01%2F3e%2F05%2F40%2Fthe-sandbar-that-links.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1913214116,3912663704&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583817452&di=b24df79306a990eef147eea14b34a9b7&imgtype=0&src=http%3A%2F%2Fmedia-cdn.tripadvisor.com%2Fmedia%2Fphoto-s%2F07%2F96%2Faf%2F8a%2Ftup-island.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583817450&di=05c5bb37bfd19afe18eb44d9b4c3e606&imgtype=0&src=http%3A%2F%2Fimages3.ctrip.com%2Fwri%2Fimages%2F200509%2FTERRKING12162233734.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3844189539,3220586590&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934165&di=30b47ac609f027f01d5f34e689cb9c48&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201505%2F17%2F20150517130829_BsaMJ.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934165&di=d4bb52ea7f8e7ea3d46798f7945a08bd&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F7Po3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2F267f9e2f07082838685c484ab999a9014c08f11f.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934163&di=7b43520d8898cd7f1d9cb9b0672a65d5&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201309%2F26%2F20130926095128_SiPMh.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934164&di=6b220374d83a7557ddb8e671cfa819ae&imgtype=0&src=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20180803%2F23%2F1533309822-GCcDphRmqw.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934163&di=a98bed131fe11a552895165fc8952087&imgtype=0&src=http%3A%2F%2Fww4.sinaimg.cn%2Fbmiddle%2F6910ab7bgw1egloghsfi3j20b40b40t6.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934162&di=4f3e247783f5711a4934304d7bedc4e9&imgtype=0&src=http%3A%2F%2Ftupian.qqw21.com%2Farticle%2FUploadPic%2F2020-4%2F2020413127077739.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934164&di=c635ac6e8aa1e67c53d4b079a5de1c7e&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fe61190ef76c6a7efd517f640fbfaaf51f3de66a6.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934162&di=4aac3e22ab161c23429d5da1439ee169&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D75ce80d1f0deb48ffb69a1d6c01e3aef%2F3e3342a7d933c895597782cbd21373f08202000f.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934162&di=16d025f7f1fa719475748579ce6526a0&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201508%2F01%2F20150801174202_UMnB8.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934160&di=b239d8a4ad0925cfa28e98780cb5d61b&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fd52a2834349b033bab65ab8516ce36d3d439bd84.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934162&di=fe73b8fc828cabbf98007af07a04a01f&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201408%2F24%2F20140824154253_45Hay.png",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590584027664&di=9222d25503f886f2cfbf84d1e088501f&imgtype=0&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D3854906927%2C947371630%26fm%3D214%26gp%3D0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3382255355,4231254675&fm=11&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3841498381,1000699718&fm=11&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934173&di=113c1fff9ecc74aca9892be9e3341f66&imgtype=0&src=http%3A%2F%2Fwx1.sinaimg.cn%2Forj360%2F005KAkbsly1ge3m5npex8j30rs0rs0u1.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934171&di=8845d7c8a4aac93ffff4f908b85fc4f4&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201509%2F22%2F20150922181536_xVTN4.jpeg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=261079729,2917181227&fm=11&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583817453&di=dd959ad7a8fec90be92118b274f77664&imgtype=0&src=http%3A%2F%2Fmedia-cdn.tripadvisor.com%2Fmedia%2Fphoto-s%2F01%2F3e%2F05%2F40%2Fthe-sandbar-that-links.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1913214116,3912663704&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583817452&di=b24df79306a990eef147eea14b34a9b7&imgtype=0&src=http%3A%2F%2Fmedia-cdn.tripadvisor.com%2Fmedia%2Fphoto-s%2F07%2F96%2Faf%2F8a%2Ftup-island.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583817450&di=05c5bb37bfd19afe18eb44d9b4c3e606&imgtype=0&src=http%3A%2F%2Fimages3.ctrip.com%2Fwri%2Fimages%2F200509%2FTERRKING12162233734.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3844189539,3220586590&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934165&di=30b47ac609f027f01d5f34e689cb9c48&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201505%2F17%2F20150517130829_BsaMJ.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934165&di=d4bb52ea7f8e7ea3d46798f7945a08bd&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F7Po3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2F267f9e2f07082838685c484ab999a9014c08f11f.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590583934163&di=7b43520d8898cd7f1d9cb9b0672a65d5&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201309%2F26%2F20130926095128_SiPMh.jpeg",
    };

    public static List<ActivityHome> createActivityHomeData() {
        List<ActivityHome> list = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ActivityHome activityHome = new ActivityHome("商品" + (i % 5 + 1) + "元", images[i]);
            list.add(activityHome);
        }
        return list;
    }

    public static List<HomeItem> createHomeItemData() {
        List<HomeItem> items = new ArrayList<>();

        List<ActivityHome> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ActivityHome activityHome = new ActivityHome("活动" + i, "" + images[i]);
            list.add(activityHome);
        }
        ActivityHomeItem activityHomeItem = new ActivityHomeItem(0, 1, list);
        items.add(activityHomeItem);


        List<BannerHome> bannerHomeList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BannerHome bannerHome = new BannerHome("banner图" + i, "" + images[i + 5]);
            bannerHomeList.add(bannerHome);
        }
        BannerHomeItem bannerHomeItem = new BannerHomeItem(1, 2, bannerHomeList);
        items.add(bannerHomeItem);

        List<CardHome> cardHomeList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CardHome cardHome = new CardHome("card" + i, "" + images[i + 10]);
            cardHomeList.add(cardHome);
        }
        CardHomeItem cardHomeItem = new CardHomeItem(2, 3, cardHomeList);
        items.add(cardHomeItem);

        return items;

    }


    public static List<HomeData> createHomeData() {
        List<HomeData> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            HomeData homeData = null;
            if (i % 3 == 0) {
                homeData = new HomeData(1, 1, "AAAAAA", images[i]);
            } else if (i % 3 == 1) {
                homeData = new HomeData(2, 2, "BBBBB");
            } else {
                homeData = new HomeData(3, 3, "CCCCC");
            }
            data.add(homeData);
        }
        return data;
    }
}
