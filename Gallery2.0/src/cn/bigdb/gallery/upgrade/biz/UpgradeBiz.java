package cn.bigdb.gallery.upgrade.biz;

import cn.bigdb.gallery.upgrade.entity.UpgradeResult;

public interface UpgradeBiz {

	UpgradeResult getUpgradeData(String equipId, int status, int type, long lastQueryTime);
}
