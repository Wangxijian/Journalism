package com.example.pc0.cehua.journalism.bean;

/**
 * Created by zxsc on 2016/3/30.
 */
public class MyInfo {

    /**
     * versionCode : 3
     * updateInfo : 版本内容：加入了XXXXXX功能
     * apkadress : http://gdown.baidu.com/data/wisegame/b50cdd3d72d80935/QQ_336.apk
     */

    private AppinfoEntity appinfo;

    public void setAppinfo(AppinfoEntity appinfo) {
        this.appinfo = appinfo;
    }

    public AppinfoEntity getAppinfo() {
        return appinfo;
    }

    public static class AppinfoEntity {
        private String versionCode;
        private String updateInfo;
        private String apkadress;

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public void setUpdateInfo(String updateInfo) {
            this.updateInfo = updateInfo;
        }

        public void setApkadress(String apkadress) {
            this.apkadress = apkadress;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public String getUpdateInfo() {
            return updateInfo;
        }

        public String getApkadress() {
            return apkadress;
        }

        @Override
        public String toString() {
            return "AppinfoEntity{" +
                    "versionCode='" + versionCode + '\'' +
                    ", updateInfo='" + updateInfo + '\'' +
                    ", apkadress='" + apkadress + '\'' +
                    '}';
        }
    }
}
