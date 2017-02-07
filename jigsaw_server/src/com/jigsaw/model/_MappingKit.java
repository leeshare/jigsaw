package com.jigsaw.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("Passport_Signin_Info", "SIGNIN_ID", PassportSigninInfo.class);
		arp.addMapping("Passport_Ticket", "APP_SIGNIN_ID", PassportTicket.class);
		arp.addMapping("Site_User", "UserID", SiteUser.class);
	}
}

