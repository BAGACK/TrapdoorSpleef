package com.comze_instancelabs.trapdoorspleef.nms;

import com.comze_instancelabs.minigamesapi.MinecraftVersionsType;

public class VersionManager {

	public static NMSAbstraction getNMSHandler(MinecraftVersionsType serverVersion) {
		switch (serverVersion)
		{
		case Unknown:
		default:
			break;
		case V1_12_R1:
		case V1_12:
			return new NMSHandler112();
		case V1_11_R1:
		case V1_11:
			return new NMSHandler111();
		case V1_10_R1:
		case V1_10:
			return new NMSHandler110();
		case V1_7:
		case V1_7_R1:
			return new NMSHandler172();
		case V1_7_R2:
			return new NMSHandler175();
		case V1_7_R3:
			return new NMSHandler178();
		case V1_7_R4:
			return new NMSHandler1710();
		case V1_8:
		case V1_8_R1:
			return new NMSHandler18();
		case V1_8_R2:
			return new NMSHandler185();
		case V1_8_R3:
			return new NMSHandler188();
		case V1_9:
		case V1_9_R1:
			return new NMSHandler19();
		case V1_9_R2:
			return new NMSHandler194();
		}
		return null;
	}

}
