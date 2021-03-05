/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core;

import java.util.Map;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;

abstract class CefactEngineeringUnits {
    private static final String CEFACT_NAMESPACE_URI = "http://www.opcfoundation.org/UA/units/un/cefact";

    private static final Map<Integer, EUInformation> BY_UNIT_ID = Maps.newConcurrentMap();

    public static final EUInformation CODE_C81 = new EUInformation(CEFACT_NAMESPACE_URI, 4405297, LocalizedText.english("rad"), LocalizedText.english("radian"));

    public static final EUInformation CODE_C25 = new EUInformation(CEFACT_NAMESPACE_URI, 4403765, LocalizedText.english("mrad"), LocalizedText.english("milliradian"));

    public static final EUInformation CODE_B97 = new EUInformation(CEFACT_NAMESPACE_URI, 4340023, LocalizedText.english("µrad"), LocalizedText.english("microradian"));

    public static final EUInformation CODE_DD = new EUInformation(CEFACT_NAMESPACE_URI, 17476, LocalizedText.english("°"), LocalizedText.english("degree [unit of angle]"));

    public static final EUInformation CODE_D61 = new EUInformation(CEFACT_NAMESPACE_URI, 4470321, LocalizedText.english("'"), LocalizedText.english("minute [unit of angle]"));

    public static final EUInformation CODE_D62 = new EUInformation(CEFACT_NAMESPACE_URI, 4470322, LocalizedText.english("\""), LocalizedText.english("second [unit of angle]"));

    public static final EUInformation CODE_A91 = new EUInformation(CEFACT_NAMESPACE_URI, 4274481, LocalizedText.english("gon"), LocalizedText.english("gon"));

    public static final EUInformation CODE_M43 = new EUInformation(CEFACT_NAMESPACE_URI, 5059635, LocalizedText.english("mil"), LocalizedText.english("mil"));

    public static final EUInformation CODE_M44 = new EUInformation(CEFACT_NAMESPACE_URI, 5059636, LocalizedText.english("rev"), LocalizedText.english("revolution"));

    public static final EUInformation CODE_D27 = new EUInformation(CEFACT_NAMESPACE_URI, 4469303, LocalizedText.english("sr"), LocalizedText.english("steradian"));

    public static final EUInformation CODE_H57 = new EUInformation(CEFACT_NAMESPACE_URI, 4732215, LocalizedText.english("in/revolution"), LocalizedText.english("inch per two pi radiant"));

    public static final EUInformation CODE_MTR = new EUInformation(CEFACT_NAMESPACE_URI, 5067858, LocalizedText.english("m"), LocalizedText.english("metre"));

    public static final EUInformation CODE_E96 = new EUInformation(CEFACT_NAMESPACE_URI, 4536630, LocalizedText.english("°/s"), LocalizedText.english("degree per second"));

    public static final EUInformation CODE_H27 = new EUInformation(CEFACT_NAMESPACE_URI, 4731447, LocalizedText.english("°/m"), LocalizedText.english("degree per metre"));

    public static final EUInformation CODE_M55 = new EUInformation(CEFACT_NAMESPACE_URI, 5059893, LocalizedText.english("m/rad"), LocalizedText.english("metre per radiant"));

    public static final EUInformation CODE_DMT = new EUInformation(CEFACT_NAMESPACE_URI, 4476244, LocalizedText.english("dm"), LocalizedText.english("decimetre"));

    public static final EUInformation CODE_CMT = new EUInformation(CEFACT_NAMESPACE_URI, 4410708, LocalizedText.english("cm"), LocalizedText.english("centimetre"));

    public static final EUInformation CODE_4H = new EUInformation(CEFACT_NAMESPACE_URI, 13384, LocalizedText.english("µm"), LocalizedText.english("micrometre (micron)"));

    public static final EUInformation CODE_MMT = new EUInformation(CEFACT_NAMESPACE_URI, 5066068, LocalizedText.english("mm"), LocalizedText.english("millimetre"));

    public static final EUInformation CODE_HMT = new EUInformation(CEFACT_NAMESPACE_URI, 4738388, LocalizedText.english("hm"), LocalizedText.english("hectometre"));

    public static final EUInformation CODE_KMT = new EUInformation(CEFACT_NAMESPACE_URI, 4934996, LocalizedText.english("km"), LocalizedText.english("kilometre"));

    public static final EUInformation CODE_C45 = new EUInformation(CEFACT_NAMESPACE_URI, 4404277, LocalizedText.english("nm"), LocalizedText.english("nanometre"));

    public static final EUInformation CODE_C52 = new EUInformation(CEFACT_NAMESPACE_URI, 4404530, LocalizedText.english("pm"), LocalizedText.english("picometre"));

    public static final EUInformation CODE_A71 = new EUInformation(CEFACT_NAMESPACE_URI, 4273969, LocalizedText.english("fm"), LocalizedText.english("femtometre"));

    public static final EUInformation CODE_A45 = new EUInformation(CEFACT_NAMESPACE_URI, 4273205, LocalizedText.english("dam"), LocalizedText.english("decametre"));

    public static final EUInformation CODE_NMI = new EUInformation(CEFACT_NAMESPACE_URI, 5131593, LocalizedText.english("n mile"), LocalizedText.english("nautical mile"));

    public static final EUInformation CODE_A11 = new EUInformation(CEFACT_NAMESPACE_URI, 4272433, LocalizedText.english("Å"), LocalizedText.english("angstrom"));

    public static final EUInformation CODE_A12 = new EUInformation(CEFACT_NAMESPACE_URI, 4272434, LocalizedText.english("ua"), LocalizedText.english("astronomical unit"));

    public static final EUInformation CODE_C63 = new EUInformation(CEFACT_NAMESPACE_URI, 4404787, LocalizedText.english("pc"), LocalizedText.english("parsec"));

    public static final EUInformation CODE_F52 = new EUInformation(CEFACT_NAMESPACE_URI, 4601138, LocalizedText.english("m/K"), LocalizedText.english("metre per kelvin"));

    public static final EUInformation CODE_F50 = new EUInformation(CEFACT_NAMESPACE_URI, 4601136, LocalizedText.english("µm/K"), LocalizedText.english("micrometre per kelvin"));

    public static final EUInformation CODE_F51 = new EUInformation(CEFACT_NAMESPACE_URI, 4601137, LocalizedText.english("cm/K"), LocalizedText.english("centimetre per kelvin"));

    public static final EUInformation CODE_G06 = new EUInformation(CEFACT_NAMESPACE_URI, 4665398, LocalizedText.english("mm/bar"), LocalizedText.english("millimetre per bar"));

    public static final EUInformation CODE_H84 = new EUInformation(CEFACT_NAMESPACE_URI, 4732980, LocalizedText.english("g·mm"), LocalizedText.english("gram millimetre"));

    public static final EUInformation CODE_G04 = new EUInformation(CEFACT_NAMESPACE_URI, 4665396, LocalizedText.english("cm/bar"), LocalizedText.english("centimetre per bar"));

    public static final EUInformation CODE_G05 = new EUInformation(CEFACT_NAMESPACE_URI, 4665397, LocalizedText.english("m/bar"), LocalizedText.english("metre per bar"));

    public static final EUInformation CODE_H79 = new EUInformation(CEFACT_NAMESPACE_URI, 4732729, LocalizedText.english("Fg"), LocalizedText.english("French gauge"));

    public static final EUInformation CODE_AK = new EUInformation(CEFACT_NAMESPACE_URI, 16715, LocalizedText.english("fth"), LocalizedText.english("fathom"));

    public static final EUInformation CODE_X1 = new EUInformation(CEFACT_NAMESPACE_URI, 22577, LocalizedText.english("ch (UK)"), LocalizedText.english("Gunter's chain"));

    public static final EUInformation CODE_INH = new EUInformation(CEFACT_NAMESPACE_URI, 4804168, LocalizedText.english("in"), LocalizedText.english("inch"));

    public static final EUInformation CODE_M7 = new EUInformation(CEFACT_NAMESPACE_URI, 19767, LocalizedText.english("µin"), LocalizedText.english("micro-inch"));

    public static final EUInformation CODE_FOT = new EUInformation(CEFACT_NAMESPACE_URI, 4607828, LocalizedText.english("ft"), LocalizedText.english("foot"));

    public static final EUInformation CODE_YRD = new EUInformation(CEFACT_NAMESPACE_URI, 5853764, LocalizedText.english("yd"), LocalizedText.english("yard"));

    public static final EUInformation CODE_SMI = new EUInformation(CEFACT_NAMESPACE_URI, 5459273, LocalizedText.english("mile"), LocalizedText.english("mile (statute mile)"));

    public static final EUInformation CODE_77 = new EUInformation(CEFACT_NAMESPACE_URI, 14135, LocalizedText.english("mil"), LocalizedText.english("milli-inch"));

    public static final EUInformation CODE_B57 = new EUInformation(CEFACT_NAMESPACE_URI, 4338999, LocalizedText.english("ly"), LocalizedText.english("light year"));

    public static final EUInformation CODE_F49 = new EUInformation(CEFACT_NAMESPACE_URI, 4600889, LocalizedText.english("rd (US)"), LocalizedText.english("rod [unit of distance]"));

    public static final EUInformation CODE_MAM = new EUInformation(CEFACT_NAMESPACE_URI, 5062989, LocalizedText.english("Mm"), LocalizedText.english("megametre"));

    public static final EUInformation CODE_K13 = new EUInformation(CEFACT_NAMESPACE_URI, 4927795, LocalizedText.english("ft/°F"), LocalizedText.english("foot per degree Fahrenheit"));

    public static final EUInformation CODE_K17 = new EUInformation(CEFACT_NAMESPACE_URI, 4927799, LocalizedText.english("ft/psi"), LocalizedText.english("foot per psi"));

    public static final EUInformation CODE_K45 = new EUInformation(CEFACT_NAMESPACE_URI, 4928565, LocalizedText.english("in/°F"), LocalizedText.english("inch per degree Fahrenheit"));

    public static final EUInformation CODE_K46 = new EUInformation(CEFACT_NAMESPACE_URI, 4928566, LocalizedText.english("in/psi"), LocalizedText.english("inch per psi"));

    public static final EUInformation CODE_L98 = new EUInformation(CEFACT_NAMESPACE_URI, 4995384, LocalizedText.english("yd/°F"), LocalizedText.english("yard per degree Fahrenheit"));

    public static final EUInformation CODE_L99 = new EUInformation(CEFACT_NAMESPACE_URI, 4995385, LocalizedText.english("yd/psi"), LocalizedText.english("yard per psi"));

    public static final EUInformation CODE_M49 = new EUInformation(CEFACT_NAMESPACE_URI, 5059641, LocalizedText.english("ch (US survey)"), LocalizedText.english("chain (based on U.S. survey foot)"));

    public static final EUInformation CODE_M50 = new EUInformation(CEFACT_NAMESPACE_URI, 5059888, LocalizedText.english("fur"), LocalizedText.english("furlong"));

    public static final EUInformation CODE_M51 = new EUInformation(CEFACT_NAMESPACE_URI, 5059889, LocalizedText.english("ft (US survey)"), LocalizedText.english("foot (U.S. survey)"));

    public static final EUInformation CODE_M52 = new EUInformation(CEFACT_NAMESPACE_URI, 5059890, LocalizedText.english("mi (US survey)"), LocalizedText.english("mile (based on U.S. survey foot)"));

    public static final EUInformation CODE_M53 = new EUInformation(CEFACT_NAMESPACE_URI, 5059891, LocalizedText.english("m/Pa"), LocalizedText.english("metre per pascal"));

    public static final EUInformation CODE_MTK = new EUInformation(CEFACT_NAMESPACE_URI, 5067851, LocalizedText.english("m²"), LocalizedText.english("square metre"));

    public static final EUInformation CODE_KMK = new EUInformation(CEFACT_NAMESPACE_URI, 4934987, LocalizedText.english("km²"), LocalizedText.english("square kilometre"));

    public static final EUInformation CODE_H30 = new EUInformation(CEFACT_NAMESPACE_URI, 4731696, LocalizedText.english("µm²"), LocalizedText.english("square micrometre (square micron)"));

    public static final EUInformation CODE_H59 = new EUInformation(CEFACT_NAMESPACE_URI, 4732217, LocalizedText.english("m²/N"), LocalizedText.english("square metre per newton"));

    public static final EUInformation CODE_DAA = new EUInformation(CEFACT_NAMESPACE_URI, 4473153, LocalizedText.english("daa"), LocalizedText.english("decare"));

    public static final EUInformation CODE_CMK = new EUInformation(CEFACT_NAMESPACE_URI, 4410699, LocalizedText.english("cm²"), LocalizedText.english("square centimetre"));

    public static final EUInformation CODE_DMK = new EUInformation(CEFACT_NAMESPACE_URI, 4476235, LocalizedText.english("dm²"), LocalizedText.english("square decimetre"));

    public static final EUInformation CODE_H16 = new EUInformation(CEFACT_NAMESPACE_URI, 4731190, LocalizedText.english("dam²"), LocalizedText.english("square decametre"));

    public static final EUInformation CODE_H18 = new EUInformation(CEFACT_NAMESPACE_URI, 4731192, LocalizedText.english("hm²"), LocalizedText.english("square hectometre"));

    public static final EUInformation CODE_MMK = new EUInformation(CEFACT_NAMESPACE_URI, 5066059, LocalizedText.english("mm²"), LocalizedText.english("square millimetre"));

    public static final EUInformation CODE_ARE = new EUInformation(CEFACT_NAMESPACE_URI, 4280901, LocalizedText.english("a"), LocalizedText.english("are"));

    public static final EUInformation CODE_HAR = new EUInformation(CEFACT_NAMESPACE_URI, 4735314, LocalizedText.english("ha"), LocalizedText.english("hectare"));

    public static final EUInformation CODE_INK = new EUInformation(CEFACT_NAMESPACE_URI, 4804171, LocalizedText.english("in²"), LocalizedText.english("square inch"));

    public static final EUInformation CODE_FTK = new EUInformation(CEFACT_NAMESPACE_URI, 4609099, LocalizedText.english("ft²"), LocalizedText.english("square foot"));

    public static final EUInformation CODE_YDK = new EUInformation(CEFACT_NAMESPACE_URI, 5850187, LocalizedText.english("yd²"), LocalizedText.english("square yard"));

    public static final EUInformation CODE_MIK = new EUInformation(CEFACT_NAMESPACE_URI, 5065035, LocalizedText.english("mi²"), LocalizedText.english("square mile (statute mile)"));

    public static final EUInformation CODE_M48 = new EUInformation(CEFACT_NAMESPACE_URI, 5059640, LocalizedText.english("mi² (US survey)"), LocalizedText.english("square mile (based on U.S. survey foot)"));

    public static final EUInformation CODE_ACR = new EUInformation(CEFACT_NAMESPACE_URI, 4277074, LocalizedText.english("acre"), LocalizedText.english("acre"));

    public static final EUInformation CODE_M47 = new EUInformation(CEFACT_NAMESPACE_URI, 5059639, LocalizedText.english("cmil"), LocalizedText.english("circular mil"));

    public static final EUInformation CODE_MTQ = new EUInformation(CEFACT_NAMESPACE_URI, 5067857, LocalizedText.english("m³"), LocalizedText.english("cubic metre"));

    public static final EUInformation CODE_MAL = new EUInformation(CEFACT_NAMESPACE_URI, 5062988, LocalizedText.english("Ml"), LocalizedText.english("megalitre"));

    public static final EUInformation CODE_LTR = new EUInformation(CEFACT_NAMESPACE_URI, 5002322, LocalizedText.english("l"), LocalizedText.english("litre"));

    public static final EUInformation CODE_MMQ = new EUInformation(CEFACT_NAMESPACE_URI, 5066065, LocalizedText.english("mm³"), LocalizedText.english("cubic millimetre"));

    public static final EUInformation CODE_CMQ = new EUInformation(CEFACT_NAMESPACE_URI, 4410705, LocalizedText.english("cm³"), LocalizedText.english("cubic centimetre"));

    public static final EUInformation CODE_DMQ = new EUInformation(CEFACT_NAMESPACE_URI, 4476241, LocalizedText.english("dm³"), LocalizedText.english("cubic decimetre"));

    public static final EUInformation CODE_MLT = new EUInformation(CEFACT_NAMESPACE_URI, 5065812, LocalizedText.english("ml"), LocalizedText.english("millilitre"));

    public static final EUInformation CODE_HLT = new EUInformation(CEFACT_NAMESPACE_URI, 4738132, LocalizedText.english("hl"), LocalizedText.english("hectolitre"));

    public static final EUInformation CODE_CLT = new EUInformation(CEFACT_NAMESPACE_URI, 4410452, LocalizedText.english("cl"), LocalizedText.english("centilitre"));

    public static final EUInformation CODE_DMA = new EUInformation(CEFACT_NAMESPACE_URI, 4476225, LocalizedText.english("dam³"), LocalizedText.english("cubic decametre"));

    public static final EUInformation CODE_H19 = new EUInformation(CEFACT_NAMESPACE_URI, 4731193, LocalizedText.english("hm³"), LocalizedText.english("cubic hectometre"));

    public static final EUInformation CODE_H20 = new EUInformation(CEFACT_NAMESPACE_URI, 4731440, LocalizedText.english("km³"), LocalizedText.english("cubic kilometre"));

    public static final EUInformation CODE_M71 = new EUInformation(CEFACT_NAMESPACE_URI, 5060401, LocalizedText.english("m³/Pa"), LocalizedText.english("cubic metre per pascal"));

    public static final EUInformation CODE_DLT = new EUInformation(CEFACT_NAMESPACE_URI, 4475988, LocalizedText.english("dl"), LocalizedText.english("decilitre"));

    public static final EUInformation CODE_4G = new EUInformation(CEFACT_NAMESPACE_URI, 13383, LocalizedText.english("µl"), LocalizedText.english("microlitre"));

    public static final EUInformation CODE_K6 = new EUInformation(CEFACT_NAMESPACE_URI, 19254, LocalizedText.english("kl"), LocalizedText.english("kilolitre"));

    public static final EUInformation CODE_A44 = new EUInformation(CEFACT_NAMESPACE_URI, 4273204, LocalizedText.english("dal"), LocalizedText.english("decalitre"));

    public static final EUInformation CODE_G94 = new EUInformation(CEFACT_NAMESPACE_URI, 4667700, LocalizedText.english("cm³/bar"), LocalizedText.english("cubic centimetre per bar"));

    public static final EUInformation CODE_G95 = new EUInformation(CEFACT_NAMESPACE_URI, 4667701, LocalizedText.english("l/bar"), LocalizedText.english("litre per bar"));

    public static final EUInformation CODE_G96 = new EUInformation(CEFACT_NAMESPACE_URI, 4667702, LocalizedText.english("m³/bar"), LocalizedText.english("cubic metre per bar"));

    public static final EUInformation CODE_G97 = new EUInformation(CEFACT_NAMESPACE_URI, 4667703, LocalizedText.english("ml/bar"), LocalizedText.english("millilitre per bar"));

    public static final EUInformation CODE_INQ = new EUInformation(CEFACT_NAMESPACE_URI, 4804177, LocalizedText.english("in³"), LocalizedText.english("cubic inch"));

    public static final EUInformation CODE_FTQ = new EUInformation(CEFACT_NAMESPACE_URI, 4609105, LocalizedText.english("ft³"), LocalizedText.english("cubic foot"));

    public static final EUInformation CODE_YDQ = new EUInformation(CEFACT_NAMESPACE_URI, 5850193, LocalizedText.english("yd³"), LocalizedText.english("cubic yard"));

    public static final EUInformation CODE_GLI = new EUInformation(CEFACT_NAMESPACE_URI, 4672585, LocalizedText.english("gal (UK)"), LocalizedText.english("gallon (UK)"));

    public static final EUInformation CODE_GLL = new EUInformation(CEFACT_NAMESPACE_URI, 4672588, LocalizedText.english("gal (US)"), LocalizedText.english("gallon (US)"));

    public static final EUInformation CODE_PT = new EUInformation(CEFACT_NAMESPACE_URI, 20564, LocalizedText.english("pt (US)"), LocalizedText.english("pint (US)"));

    public static final EUInformation CODE_PTI = new EUInformation(CEFACT_NAMESPACE_URI, 5264457, LocalizedText.english("pt (UK)"), LocalizedText.english("pint (UK)"));

    public static final EUInformation CODE_QTI = new EUInformation(CEFACT_NAMESPACE_URI, 5329993, LocalizedText.english("qt (UK)"), LocalizedText.english("quart (UK)"));

    public static final EUInformation CODE_PTL = new EUInformation(CEFACT_NAMESPACE_URI, 5264460, LocalizedText.english("liq pt (US)"), LocalizedText.english("liquid pint (US)"));

    public static final EUInformation CODE_QTL = new EUInformation(CEFACT_NAMESPACE_URI, 5329996, LocalizedText.english("liq qt (US)"), LocalizedText.english("liquid quart (US)"));

    public static final EUInformation CODE_PTD = new EUInformation(CEFACT_NAMESPACE_URI, 5264452, LocalizedText.english("dry pt (US)"), LocalizedText.english("dry pint (US)"));

    public static final EUInformation CODE_OZI = new EUInformation(CEFACT_NAMESPACE_URI, 5200457, LocalizedText.english("fl oz (UK)"), LocalizedText.english("fluid ounce (UK)"));

    public static final EUInformation CODE_QT = new EUInformation(CEFACT_NAMESPACE_URI, 20820, LocalizedText.english("qt (US)"), LocalizedText.english("quart (US)"));

    public static final EUInformation CODE_J57 = new EUInformation(CEFACT_NAMESPACE_URI, 4863287, LocalizedText.english("bbl (UK liq.)"), LocalizedText.english("barrel (UK petroleum)"));

    public static final EUInformation CODE_K21 = new EUInformation(CEFACT_NAMESPACE_URI, 4928049, LocalizedText.english("ft³/°F"), LocalizedText.english("cubic foot per degree Fahrenheit"));

    public static final EUInformation CODE_K23 = new EUInformation(CEFACT_NAMESPACE_URI, 4928051, LocalizedText.english("ft³/psi"), LocalizedText.english("cubic foot per psi"));

    public static final EUInformation CODE_L43 = new EUInformation(CEFACT_NAMESPACE_URI, 4994099, LocalizedText.english("pk (UK)"), LocalizedText.english("peck (UK)"));

    public static final EUInformation CODE_L84 = new EUInformation(CEFACT_NAMESPACE_URI, 4995124, LocalizedText.english("British shipping ton"), LocalizedText.english("ton (UK shipping)"));

    public static final EUInformation CODE_L86 = new EUInformation(CEFACT_NAMESPACE_URI, 4995126, LocalizedText.english("(US) shipping ton"), LocalizedText.english("ton (US shipping)"));

    public static final EUInformation CODE_M11 = new EUInformation(CEFACT_NAMESPACE_URI, 5058865, LocalizedText.english("yd³/°F"), LocalizedText.english("cubic yard per degree Fahrenheit"));

    public static final EUInformation CODE_M14 = new EUInformation(CEFACT_NAMESPACE_URI, 5058868, LocalizedText.english("yd³/psi"), LocalizedText.english("cubic yard per psi"));

    public static final EUInformation CODE_OZA = new EUInformation(CEFACT_NAMESPACE_URI, 5200449, LocalizedText.english("fl oz (US)"), LocalizedText.english("fluid ounce (US)"));

    public static final EUInformation CODE_BUI = new EUInformation(CEFACT_NAMESPACE_URI, 4347209, LocalizedText.english("bushel (UK)"), LocalizedText.english("bushel (UK)"));

    public static final EUInformation CODE_BUA = new EUInformation(CEFACT_NAMESPACE_URI, 4347201, LocalizedText.english("bu (US)"), LocalizedText.english("bushel (US)"));

    public static final EUInformation CODE_BLL = new EUInformation(CEFACT_NAMESPACE_URI, 4344908, LocalizedText.english("barrel (US)"), LocalizedText.english("barrel (US)"));

    public static final EUInformation CODE_BLD = new EUInformation(CEFACT_NAMESPACE_URI, 4344900, LocalizedText.english("bbl (US)"), LocalizedText.english("dry barrel (US)"));

    public static final EUInformation CODE_GLD = new EUInformation(CEFACT_NAMESPACE_URI, 4672580, LocalizedText.english("dry gal (US)"), LocalizedText.english("dry gallon (US)"));

    public static final EUInformation CODE_QTD = new EUInformation(CEFACT_NAMESPACE_URI, 5329988, LocalizedText.english("dry qt (US)"), LocalizedText.english("dry quart (US)"));

    public static final EUInformation CODE_G26 = new EUInformation(CEFACT_NAMESPACE_URI, 4665910, LocalizedText.english("st"), LocalizedText.english("stere"));

    public static final EUInformation CODE_G21 = new EUInformation(CEFACT_NAMESPACE_URI, 4665905, LocalizedText.english("cup (US)"), LocalizedText.english("cup [unit of volume]"));

    public static final EUInformation CODE_G24 = new EUInformation(CEFACT_NAMESPACE_URI, 4665908, LocalizedText.english("tablespoon (US)"), LocalizedText.english("tablespoon (US)"));

    public static final EUInformation CODE_G25 = new EUInformation(CEFACT_NAMESPACE_URI, 4665909, LocalizedText.english("teaspoon (US)"), LocalizedText.english("teaspoon (US)"));

    public static final EUInformation CODE_G23 = new EUInformation(CEFACT_NAMESPACE_URI, 4665907, LocalizedText.english("pk (US)"), LocalizedText.english("peck"));

    public static final EUInformation CODE_M67 = new EUInformation(CEFACT_NAMESPACE_URI, 5060151, LocalizedText.english("acre-ft (US survey)"), LocalizedText.english("acre-foot (based on U.S. survey foot)"));

    public static final EUInformation CODE_M68 = new EUInformation(CEFACT_NAMESPACE_URI, 5060152, LocalizedText.english("cord"), LocalizedText.english("cord (128 ft3)"));

    public static final EUInformation CODE_M69 = new EUInformation(CEFACT_NAMESPACE_URI, 5060153, LocalizedText.english("mi³"), LocalizedText.english("cubic mile (UK statute)"));

    public static final EUInformation CODE_M70 = new EUInformation(CEFACT_NAMESPACE_URI, 5060400, LocalizedText.english("RT"), LocalizedText.english("ton, register"));

    public static final EUInformation CODE_G27 = new EUInformation(CEFACT_NAMESPACE_URI, 4665911, LocalizedText.english("cm³/K"), LocalizedText.english("cubic centimetre per kelvin"));

    public static final EUInformation CODE_G29 = new EUInformation(CEFACT_NAMESPACE_URI, 4665913, LocalizedText.english("m³/K"), LocalizedText.english("cubic metre per kelvin"));

    public static final EUInformation CODE_G28 = new EUInformation(CEFACT_NAMESPACE_URI, 4665912, LocalizedText.english("l/K"), LocalizedText.english("litre per kelvin"));

    public static final EUInformation CODE_G30 = new EUInformation(CEFACT_NAMESPACE_URI, 4666160, LocalizedText.english("ml/K"), LocalizedText.english("millilitre per kelvin"));

    public static final EUInformation CODE_J36 = new EUInformation(CEFACT_NAMESPACE_URI, 4862774, LocalizedText.english("µl/l"), LocalizedText.english("microlitre per litre"));

    public static final EUInformation CODE_J87 = new EUInformation(CEFACT_NAMESPACE_URI, 4864055, LocalizedText.english("cm³/m³"), LocalizedText.english("cubic centimetre per cubic metre"));

    public static final EUInformation CODE_J91 = new EUInformation(CEFACT_NAMESPACE_URI, 4864305, LocalizedText.english("dm³/m³"), LocalizedText.english("cubic decimetre per cubic metre"));

    public static final EUInformation CODE_K62 = new EUInformation(CEFACT_NAMESPACE_URI, 4929074, LocalizedText.english("l/l"), LocalizedText.english("litre per litre"));

    public static final EUInformation CODE_L19 = new EUInformation(CEFACT_NAMESPACE_URI, 4993337, LocalizedText.english("ml/l"), LocalizedText.english("millilitre per litre"));

    public static final EUInformation CODE_L21 = new EUInformation(CEFACT_NAMESPACE_URI, 4993585, LocalizedText.english("mm³/m³"), LocalizedText.english("cubic millimetre per cubic metre"));

    public static final EUInformation CODE_SEC = new EUInformation(CEFACT_NAMESPACE_URI, 5457219, LocalizedText.english("s"), LocalizedText.english("second [unit of time]"));

    public static final EUInformation CODE_MIN = new EUInformation(CEFACT_NAMESPACE_URI, 5065038, LocalizedText.english("min"), LocalizedText.english("minute [unit of time]"));

    public static final EUInformation CODE_HUR = new EUInformation(CEFACT_NAMESPACE_URI, 4740434, LocalizedText.english("h"), LocalizedText.english("hour"));

    public static final EUInformation CODE_DAY = new EUInformation(CEFACT_NAMESPACE_URI, 4473177, LocalizedText.english("d"), LocalizedText.english("day"));

    public static final EUInformation CODE_B52 = new EUInformation(CEFACT_NAMESPACE_URI, 4338994, LocalizedText.english("ks"), LocalizedText.english("kilosecond"));

    public static final EUInformation CODE_C26 = new EUInformation(CEFACT_NAMESPACE_URI, 4403766, LocalizedText.english("ms"), LocalizedText.english("millisecond"));

    public static final EUInformation CODE_H70 = new EUInformation(CEFACT_NAMESPACE_URI, 4732720, LocalizedText.english("ps"), LocalizedText.english("picosecond"));

    public static final EUInformation CODE_B98 = new EUInformation(CEFACT_NAMESPACE_URI, 4340024, LocalizedText.english("µs"), LocalizedText.english("microsecond"));

    public static final EUInformation CODE_C47 = new EUInformation(CEFACT_NAMESPACE_URI, 4404279, LocalizedText.english("ns"), LocalizedText.english("nanosecond"));

    public static final EUInformation CODE_WEE = new EUInformation(CEFACT_NAMESPACE_URI, 5719365, LocalizedText.english("wk"), LocalizedText.english("week"));

    public static final EUInformation CODE_MON = new EUInformation(CEFACT_NAMESPACE_URI, 5066574, LocalizedText.english("mo"), LocalizedText.english("month"));

    public static final EUInformation CODE_ANN = new EUInformation(CEFACT_NAMESPACE_URI, 4279886, LocalizedText.english("y"), LocalizedText.english("year"));

    public static final EUInformation CODE_D42 = new EUInformation(CEFACT_NAMESPACE_URI, 4469810, LocalizedText.english("y (tropical)"), LocalizedText.english("tropical year"));

    public static final EUInformation CODE_L95 = new EUInformation(CEFACT_NAMESPACE_URI, 4995381, LocalizedText.english("y (365 days)"), LocalizedText.english("common year"));

    public static final EUInformation CODE_L96 = new EUInformation(CEFACT_NAMESPACE_URI, 4995382, LocalizedText.english("y (sidereal)"), LocalizedText.english("sidereal year"));

    public static final EUInformation CODE_M56 = new EUInformation(CEFACT_NAMESPACE_URI, 5059894, LocalizedText.english("shake"), LocalizedText.english("shake"));

    public static final EUInformation CODE_2A = new EUInformation(CEFACT_NAMESPACE_URI, 12865, LocalizedText.english("rad/s"), LocalizedText.english("radian per second"));

    public static final EUInformation CODE_M46 = new EUInformation(CEFACT_NAMESPACE_URI, 5059638, LocalizedText.english("r/min"), LocalizedText.english("revolution per minute"));

    public static final EUInformation CODE_2B = new EUInformation(CEFACT_NAMESPACE_URI, 12866, LocalizedText.english("rad/s²"), LocalizedText.english("radian per second squared"));

    public static final EUInformation CODE_M45 = new EUInformation(CEFACT_NAMESPACE_URI, 5059637, LocalizedText.english("°/s²"), LocalizedText.english("degree [unit of angle] per second squared"));

    public static final EUInformation CODE_MTS = new EUInformation(CEFACT_NAMESPACE_URI, 5067859, LocalizedText.english("m/s"), LocalizedText.english("metre per second"));

    public static final EUInformation CODE_KNT = new EUInformation(CEFACT_NAMESPACE_URI, 4935252, LocalizedText.english("kn"), LocalizedText.english("knot"));

    public static final EUInformation CODE_KMH = new EUInformation(CEFACT_NAMESPACE_URI, 4934984, LocalizedText.english("km/h"), LocalizedText.english("kilometre per hour"));

    public static final EUInformation CODE_C16 = new EUInformation(CEFACT_NAMESPACE_URI, 4403510, LocalizedText.english("mm/s"), LocalizedText.english("millimetre per second"));

    public static final EUInformation CODE_2M = new EUInformation(CEFACT_NAMESPACE_URI, 12877, LocalizedText.english("cm/s"), LocalizedText.english("centimetre per second"));

    public static final EUInformation CODE_H49 = new EUInformation(CEFACT_NAMESPACE_URI, 4731961, LocalizedText.english("cm/h"), LocalizedText.english("centimetre per hour"));

    public static final EUInformation CODE_H81 = new EUInformation(CEFACT_NAMESPACE_URI, 4732977, LocalizedText.english("mm/min"), LocalizedText.english("millimetre per minute"));

    public static final EUInformation CODE_2X = new EUInformation(CEFACT_NAMESPACE_URI, 12888, LocalizedText.english("m/min"), LocalizedText.english("metre per minute"));

    public static final EUInformation CODE_M59 = new EUInformation(CEFACT_NAMESPACE_URI, 5059897, LocalizedText.english("(m/s)/Pa"), LocalizedText.english("metre per second pascal"));

    public static final EUInformation CODE_H66 = new EUInformation(CEFACT_NAMESPACE_URI, 4732470, LocalizedText.english("mm/y"), LocalizedText.english("millimetre per year"));

    public static final EUInformation CODE_H67 = new EUInformation(CEFACT_NAMESPACE_URI, 4732471, LocalizedText.english("mm/h"), LocalizedText.english("millimetre per hour"));

    public static final EUInformation CODE_FR = new EUInformation(CEFACT_NAMESPACE_URI, 18002, LocalizedText.english("ft/min"), LocalizedText.english("foot per minute"));

    public static final EUInformation CODE_IU = new EUInformation(CEFACT_NAMESPACE_URI, 18773, LocalizedText.english("in/s"), LocalizedText.english("inch per second"));

    public static final EUInformation CODE_FS = new EUInformation(CEFACT_NAMESPACE_URI, 18003, LocalizedText.english("ft/s"), LocalizedText.english("foot per second"));

    public static final EUInformation CODE_HM = new EUInformation(CEFACT_NAMESPACE_URI, 18509, LocalizedText.english("mile/h"), LocalizedText.english("mile per hour (statute mile)"));

    public static final EUInformation CODE_J84 = new EUInformation(CEFACT_NAMESPACE_URI, 4864052, LocalizedText.english("(cm/s)/K"), LocalizedText.english("centimetre per second kelvin"));

    public static final EUInformation CODE_J85 = new EUInformation(CEFACT_NAMESPACE_URI, 4864053, LocalizedText.english("(cm/s)/bar"), LocalizedText.english("centimetre per second bar"));

    public static final EUInformation CODE_K14 = new EUInformation(CEFACT_NAMESPACE_URI, 4927796, LocalizedText.english("ft/h"), LocalizedText.english("foot per hour"));

    public static final EUInformation CODE_K18 = new EUInformation(CEFACT_NAMESPACE_URI, 4927800, LocalizedText.english("(ft/s)/°F"), LocalizedText.english("foot per second degree Fahrenheit"));

    public static final EUInformation CODE_K19 = new EUInformation(CEFACT_NAMESPACE_URI, 4927801, LocalizedText.english("(ft/s)/psi"), LocalizedText.english("foot per second psi"));

    public static final EUInformation CODE_K47 = new EUInformation(CEFACT_NAMESPACE_URI, 4928567, LocalizedText.english("(in/s)/°F"), LocalizedText.english("inch per second degree Fahrenheit"));

    public static final EUInformation CODE_K48 = new EUInformation(CEFACT_NAMESPACE_URI, 4928568, LocalizedText.english("(in/s)/psi"), LocalizedText.english("inch per second psi"));

    public static final EUInformation CODE_L12 = new EUInformation(CEFACT_NAMESPACE_URI, 4993330, LocalizedText.english("(m/s)/K"), LocalizedText.english("metre per second kelvin"));

    public static final EUInformation CODE_L13 = new EUInformation(CEFACT_NAMESPACE_URI, 4993331, LocalizedText.english("(m/s)/bar"), LocalizedText.english("metre per second bar"));

    public static final EUInformation CODE_M22 = new EUInformation(CEFACT_NAMESPACE_URI, 5059122, LocalizedText.english("(ml/min)/cm²"), LocalizedText.english("millilitre per square centimetre minute"));

    public static final EUInformation CODE_M57 = new EUInformation(CEFACT_NAMESPACE_URI, 5059895, LocalizedText.english("mi/min"), LocalizedText.english("mile per minute"));

    public static final EUInformation CODE_M58 = new EUInformation(CEFACT_NAMESPACE_URI, 5059896, LocalizedText.english("mi/s"), LocalizedText.english("mile per second"));

    public static final EUInformation CODE_M60 = new EUInformation(CEFACT_NAMESPACE_URI, 5060144, LocalizedText.english("m/h"), LocalizedText.english("metre per hour"));

    public static final EUInformation CODE_M61 = new EUInformation(CEFACT_NAMESPACE_URI, 5060145, LocalizedText.english("in/y"), LocalizedText.english("inch per year"));

    public static final EUInformation CODE_M62 = new EUInformation(CEFACT_NAMESPACE_URI, 5060146, LocalizedText.english("km/s"), LocalizedText.english("kilometre per second"));

    public static final EUInformation CODE_M63 = new EUInformation(CEFACT_NAMESPACE_URI, 5060147, LocalizedText.english("in/min"), LocalizedText.english("inch per minute"));

    public static final EUInformation CODE_M64 = new EUInformation(CEFACT_NAMESPACE_URI, 5060148, LocalizedText.english("yd/s"), LocalizedText.english("yard per second"));

    public static final EUInformation CODE_M65 = new EUInformation(CEFACT_NAMESPACE_URI, 5060149, LocalizedText.english("yd/min"), LocalizedText.english("yard per minute"));

    public static final EUInformation CODE_M66 = new EUInformation(CEFACT_NAMESPACE_URI, 5060150, LocalizedText.english("yd/h"), LocalizedText.english("yard per hour"));

    public static final EUInformation CODE_MSK = new EUInformation(CEFACT_NAMESPACE_URI, 5067595, LocalizedText.english("m/s²"), LocalizedText.english("metre per second squared"));

    public static final EUInformation CODE_A76 = new EUInformation(CEFACT_NAMESPACE_URI, 4273974, LocalizedText.english("Gal"), LocalizedText.english("gal"));

    public static final EUInformation CODE_C11 = new EUInformation(CEFACT_NAMESPACE_URI, 4403505, LocalizedText.english("mGal"), LocalizedText.english("milligal"));

    public static final EUInformation CODE_M38 = new EUInformation(CEFACT_NAMESPACE_URI, 5059384, LocalizedText.english("km/s²"), LocalizedText.english("kilometre per second squared"));

    public static final EUInformation CODE_M39 = new EUInformation(CEFACT_NAMESPACE_URI, 5059385, LocalizedText.english("cm/s²"), LocalizedText.english("centimetre per second squared"));

    public static final EUInformation CODE_M41 = new EUInformation(CEFACT_NAMESPACE_URI, 5059633, LocalizedText.english("mm/s²"), LocalizedText.english("millimetre per second squared"));

    public static final EUInformation CODE_A73 = new EUInformation(CEFACT_NAMESPACE_URI, 4273971, LocalizedText.english("ft/s²"), LocalizedText.english("foot per second squared"));

    public static final EUInformation CODE_IV = new EUInformation(CEFACT_NAMESPACE_URI, 18774, LocalizedText.english("in/s²"), LocalizedText.english("inch per second squared"));

    public static final EUInformation CODE_K40 = new EUInformation(CEFACT_NAMESPACE_URI, 4928560, LocalizedText.english("gn"), LocalizedText.english("standard acceleration of free fall"));

    public static final EUInformation CODE_M40 = new EUInformation(CEFACT_NAMESPACE_URI, 5059632, LocalizedText.english("yd/s²"), LocalizedText.english("yard per second squared"));

    public static final EUInformation CODE_M42 = new EUInformation(CEFACT_NAMESPACE_URI, 5059634, LocalizedText.english("mi/s²"), LocalizedText.english("mile (statute mile) per second squared"));

    public static final EUInformation CODE_C92 = new EUInformation(CEFACT_NAMESPACE_URI, 4405554, LocalizedText.english("m⁻¹"), LocalizedText.english("reciprocal metre"));

    public static final EUInformation CODE_Q32 = new EUInformation(CEFACT_NAMESPACE_URI, 5321522, LocalizedText.english("fl"), LocalizedText.english("femtolitre"));

    public static final EUInformation CODE_Q33 = new EUInformation(CEFACT_NAMESPACE_URI, 5321523, LocalizedText.english("pl"), LocalizedText.english("picolitre"));

    public static final EUInformation CODE_Q34 = new EUInformation(CEFACT_NAMESPACE_URI, 5321524, LocalizedText.english("nl"), LocalizedText.english("nanolitre"));

    public static final EUInformation CODE_AWG = new EUInformation(CEFACT_NAMESPACE_URI, 4282183, LocalizedText.english("AWG"), LocalizedText.english("american wire gauge"));

    public static final EUInformation CODE_HTZ = new EUInformation(CEFACT_NAMESPACE_URI, 4740186, LocalizedText.english("Hz"), LocalizedText.english("hertz"));

    public static final EUInformation CODE_KHZ = new EUInformation(CEFACT_NAMESPACE_URI, 4933722, LocalizedText.english("kHz"), LocalizedText.english("kilohertz"));

    public static final EUInformation CODE_MHZ = new EUInformation(CEFACT_NAMESPACE_URI, 5064794, LocalizedText.english("MHz"), LocalizedText.english("megahertz"));

    public static final EUInformation CODE_D29 = new EUInformation(CEFACT_NAMESPACE_URI, 4469305, LocalizedText.english("THz"), LocalizedText.english("terahertz"));

    public static final EUInformation CODE_A86 = new EUInformation(CEFACT_NAMESPACE_URI, 4274230, LocalizedText.english("GHz"), LocalizedText.english("gigahertz"));

    public static final EUInformation CODE_H10 = new EUInformation(CEFACT_NAMESPACE_URI, 4731184, LocalizedText.english("1/h"), LocalizedText.english("reciprocal hour"));

    public static final EUInformation CODE_H11 = new EUInformation(CEFACT_NAMESPACE_URI, 4731185, LocalizedText.english("1/mo"), LocalizedText.english("reciprocal month"));

    public static final EUInformation CODE_H09 = new EUInformation(CEFACT_NAMESPACE_URI, 4730937, LocalizedText.english("1/y"), LocalizedText.english("reciprocal year"));

    public static final EUInformation CODE_H85 = new EUInformation(CEFACT_NAMESPACE_URI, 4732981, LocalizedText.english("1/wk"), LocalizedText.english("reciprocal week"));

    public static final EUInformation CODE_C97 = new EUInformation(CEFACT_NAMESPACE_URI, 4405559, LocalizedText.english("s⁻¹"), LocalizedText.english("reciprocal second"));

    public static final EUInformation CODE_RPS = new EUInformation(CEFACT_NAMESPACE_URI, 5394515, LocalizedText.english("r/s"), LocalizedText.english("revolutions per second"));

    public static final EUInformation CODE_RPM = new EUInformation(CEFACT_NAMESPACE_URI, 5394509, LocalizedText.english("r/min"), LocalizedText.english("revolutions per minute"));

    public static final EUInformation CODE_C94 = new EUInformation(CEFACT_NAMESPACE_URI, 4405556, LocalizedText.english("min⁻¹"), LocalizedText.english("reciprocal minute"));

    public static final EUInformation CODE_C50 = new EUInformation(CEFACT_NAMESPACE_URI, 4404528, LocalizedText.english("Np"), LocalizedText.english("neper"));

    public static final EUInformation CODE_2N = new EUInformation(CEFACT_NAMESPACE_URI, 12878, LocalizedText.english("dB"), LocalizedText.english("decibel"));

    public static final EUInformation CODE_M72 = new EUInformation(CEFACT_NAMESPACE_URI, 5060402, LocalizedText.english("B"), LocalizedText.english("bel"));

    public static final EUInformation CODE_C51 = new EUInformation(CEFACT_NAMESPACE_URI, 4404529, LocalizedText.english("Np/s"), LocalizedText.english("neper per second"));

    public static final EUInformation CODE_KGM = new EUInformation(CEFACT_NAMESPACE_URI, 4933453, LocalizedText.english("kg"), LocalizedText.english("kilogram"));

    public static final EUInformation CODE_MC = new EUInformation(CEFACT_NAMESPACE_URI, 19779, LocalizedText.english("µg"), LocalizedText.english("microgram"));

    public static final EUInformation CODE_DJ = new EUInformation(CEFACT_NAMESPACE_URI, 17482, LocalizedText.english("dag"), LocalizedText.english("decagram"));

    public static final EUInformation CODE_DG = new EUInformation(CEFACT_NAMESPACE_URI, 17479, LocalizedText.english("dg"), LocalizedText.english("decigram"));

    public static final EUInformation CODE_GRM = new EUInformation(CEFACT_NAMESPACE_URI, 4674125, LocalizedText.english("g"), LocalizedText.english("gram"));

    public static final EUInformation CODE_CGM = new EUInformation(CEFACT_NAMESPACE_URI, 4409165, LocalizedText.english("cg"), LocalizedText.english("centigram"));

    public static final EUInformation CODE_TNE = new EUInformation(CEFACT_NAMESPACE_URI, 5525061, LocalizedText.english("t"), LocalizedText.english("tonne (metric ton)"));

    public static final EUInformation CODE_DTN = new EUInformation(CEFACT_NAMESPACE_URI, 4478030, LocalizedText.english("dt or dtn"), LocalizedText.english("decitonne"));

    public static final EUInformation CODE_MGM = new EUInformation(CEFACT_NAMESPACE_URI, 5064525, LocalizedText.english("mg"), LocalizedText.english("milligram"));

    public static final EUInformation CODE_HGM = new EUInformation(CEFACT_NAMESPACE_URI, 4736845, LocalizedText.english("hg"), LocalizedText.english("hectogram"));

    public static final EUInformation CODE_KTN = new EUInformation(CEFACT_NAMESPACE_URI, 4936782, LocalizedText.english("kt"), LocalizedText.english("kilotonne"));

    public static final EUInformation CODE_2U = new EUInformation(CEFACT_NAMESPACE_URI, 12885, LocalizedText.english("Mg"), LocalizedText.english("megagram"));

    public static final EUInformation CODE_LBR = new EUInformation(CEFACT_NAMESPACE_URI, 4997714, LocalizedText.english("lb"), LocalizedText.english("pound"));

    public static final EUInformation CODE_GRN = new EUInformation(CEFACT_NAMESPACE_URI, 4674126, LocalizedText.english("gr"), LocalizedText.english("grain"));

    public static final EUInformation CODE_ONZ = new EUInformation(CEFACT_NAMESPACE_URI, 5197402, LocalizedText.english("oz"), LocalizedText.english("ounce (avoirdupois)"));

    public static final EUInformation CODE_CWI = new EUInformation(CEFACT_NAMESPACE_URI, 4413257, LocalizedText.english("cwt (UK)"), LocalizedText.english("hundred weight (UK)"));

    public static final EUInformation CODE_CWA = new EUInformation(CEFACT_NAMESPACE_URI, 4413249, LocalizedText.english("cwt (US)"), LocalizedText.english("hundred pound (cwt) / hundred weight (US)"));

    public static final EUInformation CODE_LTN = new EUInformation(CEFACT_NAMESPACE_URI, 5002318, LocalizedText.english("ton (UK)"), LocalizedText.english("ton (UK) or long ton (US)"));

    public static final EUInformation CODE_STI = new EUInformation(CEFACT_NAMESPACE_URI, 5461065, LocalizedText.english("st"), LocalizedText.english("stone (UK)"));

    public static final EUInformation CODE_STN = new EUInformation(CEFACT_NAMESPACE_URI, 5461070, LocalizedText.english("ton (US)"), LocalizedText.english("ton (US) or short ton (UK/US)"));

    public static final EUInformation CODE_APZ = new EUInformation(CEFACT_NAMESPACE_URI, 4280410, LocalizedText.english("tr oz"), LocalizedText.english("troy ounce or apothecary ounce"));

    public static final EUInformation CODE_F13 = new EUInformation(CEFACT_NAMESPACE_URI, 4600115, LocalizedText.english("slug"), LocalizedText.english("slug"));

    public static final EUInformation CODE_K64 = new EUInformation(CEFACT_NAMESPACE_URI, 4929076, LocalizedText.english("lb/°F"), LocalizedText.english("pound (avoirdupois) per degree Fahrenheit"));

    public static final EUInformation CODE_L69 = new EUInformation(CEFACT_NAMESPACE_URI, 4994617, LocalizedText.english("t/K"), LocalizedText.english("tonne per kelvin"));

    public static final EUInformation CODE_L87 = new EUInformation(CEFACT_NAMESPACE_URI, 4995127, LocalizedText.english("ton (US)/°F"), LocalizedText.english("ton short per degree Fahrenheit"));

    public static final EUInformation CODE_M86 = new EUInformation(CEFACT_NAMESPACE_URI, 5060662, LocalizedText.english("pfd"), LocalizedText.english("pfund"));

    public static final EUInformation CODE_KMQ = new EUInformation(CEFACT_NAMESPACE_URI, 4934993, LocalizedText.english("kg/m³"), LocalizedText.english("kilogram per cubic metre"));

    public static final EUInformation CODE_23 = new EUInformation(CEFACT_NAMESPACE_URI, 12851, LocalizedText.english("g/cm³"), LocalizedText.english("gram per cubic centimetre"));

    public static final EUInformation CODE_D41 = new EUInformation(CEFACT_NAMESPACE_URI, 4469809, LocalizedText.english("t/m³"), LocalizedText.english("tonne per cubic metre"));

    public static final EUInformation CODE_GJ = new EUInformation(CEFACT_NAMESPACE_URI, 18250, LocalizedText.english("g/ml"), LocalizedText.english("gram per millilitre"));

    public static final EUInformation CODE_B35 = new EUInformation(CEFACT_NAMESPACE_URI, 4338485, LocalizedText.english("kg/l or kg/L"), LocalizedText.english("kilogram per litre"));

    public static final EUInformation CODE_GL = new EUInformation(CEFACT_NAMESPACE_URI, 18252, LocalizedText.english("g/l"), LocalizedText.english("gram per litre"));

    public static final EUInformation CODE_A93 = new EUInformation(CEFACT_NAMESPACE_URI, 4274483, LocalizedText.english("g/m³"), LocalizedText.english("gram per cubic metre"));

    public static final EUInformation CODE_GP = new EUInformation(CEFACT_NAMESPACE_URI, 18256, LocalizedText.english("mg/m³"), LocalizedText.english("milligram per cubic metre"));

    public static final EUInformation CODE_B72 = new EUInformation(CEFACT_NAMESPACE_URI, 4339506, LocalizedText.english("Mg/m³"), LocalizedText.english("megagram per cubic metre"));

    public static final EUInformation CODE_B34 = new EUInformation(CEFACT_NAMESPACE_URI, 4338484, LocalizedText.english("kg/dm³"), LocalizedText.english("kilogram per cubic decimetre"));

    public static final EUInformation CODE_H64 = new EUInformation(CEFACT_NAMESPACE_URI, 4732468, LocalizedText.english("mg/g"), LocalizedText.english("milligram per gram"));

    public static final EUInformation CODE_H29 = new EUInformation(CEFACT_NAMESPACE_URI, 4731449, LocalizedText.english("µg/l"), LocalizedText.english("microgram per litre"));

    public static final EUInformation CODE_M1 = new EUInformation(CEFACT_NAMESPACE_URI, 19761, LocalizedText.english("mg/l"), LocalizedText.english("milligram per litre"));

    public static final EUInformation CODE_GQ = new EUInformation(CEFACT_NAMESPACE_URI, 18257, LocalizedText.english("µg/m³"), LocalizedText.english("microgram per cubic metre"));

    public static final EUInformation CODE_G11 = new EUInformation(CEFACT_NAMESPACE_URI, 4665649, LocalizedText.english("g/(cm³·bar)"), LocalizedText.english("gram per cubic centimetre bar"));

    public static final EUInformation CODE_G33 = new EUInformation(CEFACT_NAMESPACE_URI, 4666163, LocalizedText.english("g/(cm³·K)"), LocalizedText.english("gram per cubic centimetre kelvin"));

    public static final EUInformation CODE_F23 = new EUInformation(CEFACT_NAMESPACE_URI, 4600371, LocalizedText.english("g/dm³"), LocalizedText.english("gram per cubic decimetre"));

    public static final EUInformation CODE_G12 = new EUInformation(CEFACT_NAMESPACE_URI, 4665650, LocalizedText.english("g/(dm³·bar)"), LocalizedText.english("gram per cubic decimetre bar"));

    public static final EUInformation CODE_G34 = new EUInformation(CEFACT_NAMESPACE_URI, 4666164, LocalizedText.english("g/(dm³·K)"), LocalizedText.english("gram per cubic decimetre kelvin"));

    public static final EUInformation CODE_G14 = new EUInformation(CEFACT_NAMESPACE_URI, 4665652, LocalizedText.english("g/(m³·bar)"), LocalizedText.english("gram per cubic metre bar"));

    public static final EUInformation CODE_G36 = new EUInformation(CEFACT_NAMESPACE_URI, 4666166, LocalizedText.english("g/(m³·K)"), LocalizedText.english("gram per cubic metre kelvin"));

    public static final EUInformation CODE_G13 = new EUInformation(CEFACT_NAMESPACE_URI, 4665651, LocalizedText.english("g/(l·bar)"), LocalizedText.english("gram per litre bar"));

    public static final EUInformation CODE_G35 = new EUInformation(CEFACT_NAMESPACE_URI, 4666165, LocalizedText.english("g/(l·K)"), LocalizedText.english("gram per litre kelvin"));

    public static final EUInformation CODE_G15 = new EUInformation(CEFACT_NAMESPACE_URI, 4665653, LocalizedText.english("g/(ml·bar)"), LocalizedText.english("gram per millilitre bar"));

    public static final EUInformation CODE_G37 = new EUInformation(CEFACT_NAMESPACE_URI, 4666167, LocalizedText.english("g/(ml·K)"), LocalizedText.english("gram per millilitre kelvin"));

    public static final EUInformation CODE_G31 = new EUInformation(CEFACT_NAMESPACE_URI, 4666161, LocalizedText.english("kg/cm³"), LocalizedText.english("kilogram per cubic centimetre"));

    public static final EUInformation CODE_G16 = new EUInformation(CEFACT_NAMESPACE_URI, 4665654, LocalizedText.english("kg/(cm³·bar)"), LocalizedText.english("kilogram per cubic centimetre bar"));

    public static final EUInformation CODE_G38 = new EUInformation(CEFACT_NAMESPACE_URI, 4666168, LocalizedText.english("kg/(cm³·K)"), LocalizedText.english("kilogram per cubic centimetre kelvin"));

    public static final EUInformation CODE_G18 = new EUInformation(CEFACT_NAMESPACE_URI, 4665656, LocalizedText.english("kg/(m³·bar)"), LocalizedText.english("kilogram per cubic metre bar"));

    public static final EUInformation CODE_G40 = new EUInformation(CEFACT_NAMESPACE_URI, 4666416, LocalizedText.english("kg/(m³·K)"), LocalizedText.english("kilogram per cubic metre kelvin"));

    public static final EUInformation CODE_H54 = new EUInformation(CEFACT_NAMESPACE_URI, 4732212, LocalizedText.english("(kg/dm³)/K"), LocalizedText.english("kilogram per cubic decimetre kelvin"));

    public static final EUInformation CODE_H55 = new EUInformation(CEFACT_NAMESPACE_URI, 4732213, LocalizedText.english("(kg/dm³)/bar"), LocalizedText.english("kilogram per cubic decimetre bar"));

    public static final EUInformation CODE_F14 = new EUInformation(CEFACT_NAMESPACE_URI, 4600116, LocalizedText.english("g/K"), LocalizedText.english("gram per kelvin"));

    public static final EUInformation CODE_F15 = new EUInformation(CEFACT_NAMESPACE_URI, 4600117, LocalizedText.english("kg/K"), LocalizedText.english("kilogram per kelvin"));

    public static final EUInformation CODE_F24 = new EUInformation(CEFACT_NAMESPACE_URI, 4600372, LocalizedText.english("kg/kmol"), LocalizedText.english("kilogram per kilomol"));

    public static final EUInformation CODE_G17 = new EUInformation(CEFACT_NAMESPACE_URI, 4665655, LocalizedText.english("kg/(l·bar)"), LocalizedText.english("kilogram per litre bar"));

    public static final EUInformation CODE_G39 = new EUInformation(CEFACT_NAMESPACE_URI, 4666169, LocalizedText.english("kg/(l·K)"), LocalizedText.english("kilogram per litre kelvin"));

    public static final EUInformation CODE_H53 = new EUInformation(CEFACT_NAMESPACE_URI, 4732211, LocalizedText.english("kg/bar"), LocalizedText.english("kilogram per bar"));

    public static final EUInformation CODE_F18 = new EUInformation(CEFACT_NAMESPACE_URI, 4600120, LocalizedText.english("kg·cm²"), LocalizedText.english("kilogram square centimetre"));

    public static final EUInformation CODE_F19 = new EUInformation(CEFACT_NAMESPACE_URI, 4600121, LocalizedText.english("kg·mm²"), LocalizedText.english("kilogram square millimetre"));

    public static final EUInformation CODE_F74 = new EUInformation(CEFACT_NAMESPACE_URI, 4601652, LocalizedText.english("g/bar"), LocalizedText.english("gram per bar"));

    public static final EUInformation CODE_F75 = new EUInformation(CEFACT_NAMESPACE_URI, 4601653, LocalizedText.english("mg/bar"), LocalizedText.english("milligram per bar"));

    public static final EUInformation CODE_F16 = new EUInformation(CEFACT_NAMESPACE_URI, 4600118, LocalizedText.english("mg/K"), LocalizedText.english("milligram per kelvin"));

    public static final EUInformation CODE_M73 = new EUInformation(CEFACT_NAMESPACE_URI, 5060403, LocalizedText.english("(kg/m³)/Pa"), LocalizedText.english("kilogram per cubic metre pascal"));

    public static final EUInformation CODE_87 = new EUInformation(CEFACT_NAMESPACE_URI, 14391, LocalizedText.english("lb/ft³"), LocalizedText.english("pound per cubic foot"));

    public static final EUInformation CODE_GE = new EUInformation(CEFACT_NAMESPACE_URI, 18245, LocalizedText.english("lb/gal (US)"), LocalizedText.english("pound per gallon (US)"));

    public static final EUInformation CODE_LA = new EUInformation(CEFACT_NAMESPACE_URI, 19521, LocalizedText.english("lb/in³"), LocalizedText.english("pound per cubic inch"));

    public static final EUInformation CODE_G32 = new EUInformation(CEFACT_NAMESPACE_URI, 4666162, LocalizedText.english("oz/yd³"), LocalizedText.english("ounce (avoirdupois) per cubic yard"));

    public static final EUInformation CODE_J34 = new EUInformation(CEFACT_NAMESPACE_URI, 4862772, LocalizedText.english("(µg/m³)/K"), LocalizedText.english("microgram per cubic metre kelvin"));

    public static final EUInformation CODE_J35 = new EUInformation(CEFACT_NAMESPACE_URI, 4862773, LocalizedText.english("(µg/m³)/bar"), LocalizedText.english("microgram per cubic metre bar"));

    public static final EUInformation CODE_K41 = new EUInformation(CEFACT_NAMESPACE_URI, 4928561, LocalizedText.english("gr/gal (US)"), LocalizedText.english("grain per gallon (US)"));

    public static final EUInformation CODE_K69 = new EUInformation(CEFACT_NAMESPACE_URI, 4929081, LocalizedText.english("(lb/ft³)/°F"), LocalizedText.english("pound (avoirdupois) per cubic foot degree Fahrenheit"));

    public static final EUInformation CODE_K70 = new EUInformation(CEFACT_NAMESPACE_URI, 4929328, LocalizedText.english("(lb/ft³)/psi"), LocalizedText.english("pound (avoirdupois) per cubic foot psi"));

    public static final EUInformation CODE_K71 = new EUInformation(CEFACT_NAMESPACE_URI, 4929329, LocalizedText.english("lb/gal (UK)"), LocalizedText.english("pound (avoirdupois) per gallon (UK)"));

    public static final EUInformation CODE_K75 = new EUInformation(CEFACT_NAMESPACE_URI, 4929333, LocalizedText.english("(lb/in³)/°F"), LocalizedText.english("pound (avoirdupois) per cubic inch degree Fahrenheit"));

    public static final EUInformation CODE_K76 = new EUInformation(CEFACT_NAMESPACE_URI, 4929334, LocalizedText.english("(lb/in³)/psi"), LocalizedText.english("pound (avoirdupois) per cubic inch psi"));

    public static final EUInformation CODE_K84 = new EUInformation(CEFACT_NAMESPACE_URI, 4929588, LocalizedText.english("lb/yd³"), LocalizedText.english("pound per cubic yard"));

    public static final EUInformation CODE_L17 = new EUInformation(CEFACT_NAMESPACE_URI, 4993335, LocalizedText.english("(mg/m³)/K"), LocalizedText.english("milligram per cubic metre kelvin"));

    public static final EUInformation CODE_L18 = new EUInformation(CEFACT_NAMESPACE_URI, 4993336, LocalizedText.english("(mg/m³)/bar"), LocalizedText.english("milligram per cubic metre bar"));

    public static final EUInformation CODE_L37 = new EUInformation(CEFACT_NAMESPACE_URI, 4993847, LocalizedText.english("oz/gal (UK)"), LocalizedText.english("ounce (avoirdupois) per gallon (UK)"));

    public static final EUInformation CODE_L38 = new EUInformation(CEFACT_NAMESPACE_URI, 4993848, LocalizedText.english("oz/gal (US)"), LocalizedText.english("ounce (avoirdupois) per gallon (US)"));

    public static final EUInformation CODE_L39 = new EUInformation(CEFACT_NAMESPACE_URI, 4993849, LocalizedText.english("oz/in³"), LocalizedText.english("ounce (avoirdupois) per cubic inch"));

    public static final EUInformation CODE_L65 = new EUInformation(CEFACT_NAMESPACE_URI, 4994613, LocalizedText.english("slug/ft³"), LocalizedText.english("slug per cubic foot"));

    public static final EUInformation CODE_L76 = new EUInformation(CEFACT_NAMESPACE_URI, 4994870, LocalizedText.english("(t/m³)/K"), LocalizedText.english("tonne per cubic metre kelvin"));

    public static final EUInformation CODE_L77 = new EUInformation(CEFACT_NAMESPACE_URI, 4994871, LocalizedText.english("(t/m³)/bar"), LocalizedText.english("tonne per cubic metre bar"));

    public static final EUInformation CODE_L92 = new EUInformation(CEFACT_NAMESPACE_URI, 4995378, LocalizedText.english("ton.l/yd³ (UK)"), LocalizedText.english("ton (UK long) per cubic yard"));

    public static final EUInformation CODE_L93 = new EUInformation(CEFACT_NAMESPACE_URI, 4995379, LocalizedText.english("ton.s/yd³ (US)"), LocalizedText.english("ton (US short) per cubic yard"));

    public static final EUInformation CODE_K77 = new EUInformation(CEFACT_NAMESPACE_URI, 4929335, LocalizedText.english("lb/psi"), LocalizedText.english("pound (avoirdupois) per psi"));

    public static final EUInformation CODE_L70 = new EUInformation(CEFACT_NAMESPACE_URI, 4994864, LocalizedText.english("t/bar"), LocalizedText.english("tonne per bar"));

    public static final EUInformation CODE_L91 = new EUInformation(CEFACT_NAMESPACE_URI, 4995377, LocalizedText.english("ton (US)/psi"), LocalizedText.english("ton short per psi"));

    public static final EUInformation CODE_M74 = new EUInformation(CEFACT_NAMESPACE_URI, 5060404, LocalizedText.english("kg/Pa"), LocalizedText.english("kilogram per pascal"));

    public static final EUInformation CODE_C62 = new EUInformation(CEFACT_NAMESPACE_URI, 4404786, LocalizedText.english("1"), LocalizedText.english("one"));

    public static final EUInformation CODE_A39 = new EUInformation(CEFACT_NAMESPACE_URI, 4272953, LocalizedText.english("m³/kg"), LocalizedText.english("cubic metre per kilogram"));

    public static final EUInformation CODE_22 = new EUInformation(CEFACT_NAMESPACE_URI, 12850, LocalizedText.english("dl/g"), LocalizedText.english("decilitre per gram"));

    public static final EUInformation CODE_H65 = new EUInformation(CEFACT_NAMESPACE_URI, 4732469, LocalizedText.english("ml/m³"), LocalizedText.english("millilitre per cubic metre"));

    public static final EUInformation CODE_H83 = new EUInformation(CEFACT_NAMESPACE_URI, 4732979, LocalizedText.english("l/kg"), LocalizedText.english("litre per kilogram"));

    public static final EUInformation CODE_KX = new EUInformation(CEFACT_NAMESPACE_URI, 19288, LocalizedText.english("ml/kg"), LocalizedText.english("millilitre per kilogram"));

    public static final EUInformation CODE_H15 = new EUInformation(CEFACT_NAMESPACE_URI, 4731189, LocalizedText.english("cm²/g"), LocalizedText.english("square centimetre per gram"));

    public static final EUInformation CODE_N28 = new EUInformation(CEFACT_NAMESPACE_URI, 5124664, LocalizedText.english("dm³/kg"), LocalizedText.english("cubic decimetre per kilogram"));

    public static final EUInformation CODE_N29 = new EUInformation(CEFACT_NAMESPACE_URI, 5124665, LocalizedText.english("ft³/lb"), LocalizedText.english("cubic foot per pound"));

    public static final EUInformation CODE_N30 = new EUInformation(CEFACT_NAMESPACE_URI, 5124912, LocalizedText.english("in³/lb"), LocalizedText.english("cubic inch per pound"));

    public static final EUInformation CODE_KL = new EUInformation(CEFACT_NAMESPACE_URI, 19276, LocalizedText.english("kg/m"), LocalizedText.english("kilogram per metre"));

    public static final EUInformation CODE_GF = new EUInformation(CEFACT_NAMESPACE_URI, 18246, LocalizedText.english("g/m"), LocalizedText.english("gram per metre (gram per 100 centimetres)"));

    public static final EUInformation CODE_H76 = new EUInformation(CEFACT_NAMESPACE_URI, 4732726, LocalizedText.english("g/mm"), LocalizedText.english("gram per millimetre"));

    public static final EUInformation CODE_KW = new EUInformation(CEFACT_NAMESPACE_URI, 19287, LocalizedText.english("kg/mm"), LocalizedText.english("kilogram per millimetre"));

    public static final EUInformation CODE_C12 = new EUInformation(CEFACT_NAMESPACE_URI, 4403506, LocalizedText.english("mg/m"), LocalizedText.english("milligram per metre"));

    public static final EUInformation CODE_M31 = new EUInformation(CEFACT_NAMESPACE_URI, 5059377, LocalizedText.english("kg/km"), LocalizedText.english("kilogram per kilometre"));

    public static final EUInformation CODE_P2 = new EUInformation(CEFACT_NAMESPACE_URI, 20530, LocalizedText.english("lb/ft"), LocalizedText.english("pound per foot"));

    public static final EUInformation CODE_PO = new EUInformation(CEFACT_NAMESPACE_URI, 20559, LocalizedText.english("lb/in"), LocalizedText.english("pound per inch of length"));

    public static final EUInformation CODE_M83 = new EUInformation(CEFACT_NAMESPACE_URI, 5060659, LocalizedText.english("den"), LocalizedText.english("denier"));

    public static final EUInformation CODE_M84 = new EUInformation(CEFACT_NAMESPACE_URI, 5060660, LocalizedText.english("lb/yd"), LocalizedText.english("pound per yard"));

    public static final EUInformation CODE_GO = new EUInformation(CEFACT_NAMESPACE_URI, 18255, LocalizedText.english("mg/m²"), LocalizedText.english("milligram per square metre"));

    public static final EUInformation CODE_25 = new EUInformation(CEFACT_NAMESPACE_URI, 12853, LocalizedText.english("g/cm²"), LocalizedText.english("gram per square centimetre"));

    public static final EUInformation CODE_H63 = new EUInformation(CEFACT_NAMESPACE_URI, 4732467, LocalizedText.english("mg/cm²"), LocalizedText.english("milligram per square centimetre"));

    public static final EUInformation CODE_GM = new EUInformation(CEFACT_NAMESPACE_URI, 18253, LocalizedText.english("g/m²"), LocalizedText.english("gram per square metre"));

    public static final EUInformation CODE_28 = new EUInformation(CEFACT_NAMESPACE_URI, 12856, LocalizedText.english("kg/m²"), LocalizedText.english("kilogram per square metre"));

    public static final EUInformation CODE_D5 = new EUInformation(CEFACT_NAMESPACE_URI, 17461, LocalizedText.english("kg/cm²"), LocalizedText.english("kilogram per square centimetre"));

    public static final EUInformation CODE_ON = new EUInformation(CEFACT_NAMESPACE_URI, 20302, LocalizedText.english("oz/yd²"), LocalizedText.english("ounce per square yard"));

    public static final EUInformation CODE_37 = new EUInformation(CEFACT_NAMESPACE_URI, 13111, LocalizedText.english("oz/ft²"), LocalizedText.english("ounce per square foot"));

    public static final EUInformation CODE_B31 = new EUInformation(CEFACT_NAMESPACE_URI, 4338481, LocalizedText.english("kg·m/s"), LocalizedText.english("kilogram metre per second"));

    public static final EUInformation CODE_M98 = new EUInformation(CEFACT_NAMESPACE_URI, 5060920, LocalizedText.english("kg·(cm/s)"), LocalizedText.english("kilogram centimetre per second"));

    public static final EUInformation CODE_M99 = new EUInformation(CEFACT_NAMESPACE_URI, 5060921, LocalizedText.english("g·(cm/s)"), LocalizedText.english("gram centimetre per second"));

    public static final EUInformation CODE_N10 = new EUInformation(CEFACT_NAMESPACE_URI, 5124400, LocalizedText.english("lb·(ft/s)"), LocalizedText.english("pound foot per second"));

    public static final EUInformation CODE_N11 = new EUInformation(CEFACT_NAMESPACE_URI, 5124401, LocalizedText.english("lb·(in/s)"), LocalizedText.english("pound inch per second"));

    public static final EUInformation CODE_B33 = new EUInformation(CEFACT_NAMESPACE_URI, 4338483, LocalizedText.english("kg·m²/s"), LocalizedText.english("kilogram metre squared per second"));

    public static final EUInformation CODE_B32 = new EUInformation(CEFACT_NAMESPACE_URI, 4338482, LocalizedText.english("kg·m²"), LocalizedText.english("kilogram metre squared"));

    public static final EUInformation CODE_F20 = new EUInformation(CEFACT_NAMESPACE_URI, 4600368, LocalizedText.english("lb·in²"), LocalizedText.english("pound inch squared"));

    public static final EUInformation CODE_K65 = new EUInformation(CEFACT_NAMESPACE_URI, 4929077, LocalizedText.english("lb·ft²"), LocalizedText.english("pound (avoirdupois) square foot"));

    public static final EUInformation CODE_NEW = new EUInformation(CEFACT_NAMESPACE_URI, 5129559, LocalizedText.english("N"), LocalizedText.english("newton"));

    public static final EUInformation CODE_B73 = new EUInformation(CEFACT_NAMESPACE_URI, 4339507, LocalizedText.english("MN"), LocalizedText.english("meganewton"));

    public static final EUInformation CODE_B47 = new EUInformation(CEFACT_NAMESPACE_URI, 4338743, LocalizedText.english("kN"), LocalizedText.english("kilonewton"));

    public static final EUInformation CODE_C20 = new EUInformation(CEFACT_NAMESPACE_URI, 4403760, LocalizedText.english("mN"), LocalizedText.english("millinewton"));

    public static final EUInformation CODE_B92 = new EUInformation(CEFACT_NAMESPACE_URI, 4340018, LocalizedText.english("µN"), LocalizedText.english("micronewton"));

    public static final EUInformation CODE_DU = new EUInformation(CEFACT_NAMESPACE_URI, 17493, LocalizedText.english("dyn"), LocalizedText.english("dyne"));

    public static final EUInformation CODE_C78 = new EUInformation(CEFACT_NAMESPACE_URI, 4405048, LocalizedText.english("lbf"), LocalizedText.english("pound-force"));

    public static final EUInformation CODE_B37 = new EUInformation(CEFACT_NAMESPACE_URI, 4338487, LocalizedText.english("kgf"), LocalizedText.english("kilogram-force"));

    public static final EUInformation CODE_B51 = new EUInformation(CEFACT_NAMESPACE_URI, 4338993, LocalizedText.english("kp"), LocalizedText.english("kilopond"));

    public static final EUInformation CODE_L40 = new EUInformation(CEFACT_NAMESPACE_URI, 4994096, LocalizedText.english("ozf"), LocalizedText.english("ounce (avoirdupois)-force"));

    public static final EUInformation CODE_L94 = new EUInformation(CEFACT_NAMESPACE_URI, 4995380, LocalizedText.english("ton.sh-force"), LocalizedText.english("ton-force (US short)"));

    public static final EUInformation CODE_M75 = new EUInformation(CEFACT_NAMESPACE_URI, 5060405, LocalizedText.english("kip"), LocalizedText.english("kilopound-force"));

    public static final EUInformation CODE_M76 = new EUInformation(CEFACT_NAMESPACE_URI, 5060406, LocalizedText.english("pdl"), LocalizedText.english("poundal"));

    public static final EUInformation CODE_M77 = new EUInformation(CEFACT_NAMESPACE_URI, 5060407, LocalizedText.english("kg·m/s²"), LocalizedText.english("kilogram metre per second squared"));

    public static final EUInformation CODE_M78 = new EUInformation(CEFACT_NAMESPACE_URI, 5060408, LocalizedText.english("p"), LocalizedText.english("pond"));

    public static final EUInformation CODE_F17 = new EUInformation(CEFACT_NAMESPACE_URI, 4600119, LocalizedText.english("lbf/ft"), LocalizedText.english("pound-force per foot"));

    public static final EUInformation CODE_F48 = new EUInformation(CEFACT_NAMESPACE_URI, 4600888, LocalizedText.english("lbf/in"), LocalizedText.english("pound-force per inch"));

    public static final EUInformation CODE_C54 = new EUInformation(CEFACT_NAMESPACE_URI, 4404532, LocalizedText.english("N·m²/kg²"), LocalizedText.english("newton metre squared per kilogram squared"));

    public static final EUInformation CODE_NU = new EUInformation(CEFACT_NAMESPACE_URI, 20053, LocalizedText.english("N·m"), LocalizedText.english("newton metre"));

    public static final EUInformation CODE_H40 = new EUInformation(CEFACT_NAMESPACE_URI, 4731952, LocalizedText.english("N/A"), LocalizedText.english("newton per ampere"));

    public static final EUInformation CODE_B74 = new EUInformation(CEFACT_NAMESPACE_URI, 4339508, LocalizedText.english("MN·m"), LocalizedText.english("meganewton metre"));

    public static final EUInformation CODE_B48 = new EUInformation(CEFACT_NAMESPACE_URI, 4338744, LocalizedText.english("kN·m"), LocalizedText.english("kilonewton metre"));

    public static final EUInformation CODE_D83 = new EUInformation(CEFACT_NAMESPACE_URI, 4470835, LocalizedText.english("mN·m"), LocalizedText.english("millinewton metre"));

    public static final EUInformation CODE_B93 = new EUInformation(CEFACT_NAMESPACE_URI, 4340019, LocalizedText.english("µN·m"), LocalizedText.english("micronewton metre"));

    public static final EUInformation CODE_DN = new EUInformation(CEFACT_NAMESPACE_URI, 17486, LocalizedText.english("dN·m"), LocalizedText.english("decinewton metre"));

    public static final EUInformation CODE_J72 = new EUInformation(CEFACT_NAMESPACE_URI, 4863794, LocalizedText.english("cN·m"), LocalizedText.english("centinewton metre"));

    public static final EUInformation CODE_M94 = new EUInformation(CEFACT_NAMESPACE_URI, 5060916, LocalizedText.english("kg·m"), LocalizedText.english("kilogram metre"));

    public static final EUInformation CODE_F88 = new EUInformation(CEFACT_NAMESPACE_URI, 4601912, LocalizedText.english("N·cm"), LocalizedText.english("newton centimetre"));

    public static final EUInformation CODE_F90 = new EUInformation(CEFACT_NAMESPACE_URI, 4602160, LocalizedText.english("N·m/A"), LocalizedText.english("newton metre per ampere"));

    public static final EUInformation CODE_F89 = new EUInformation(CEFACT_NAMESPACE_URI, 4601913, LocalizedText.english("Nm/°"), LocalizedText.english("newton metre per degree"));

    public static final EUInformation CODE_G19 = new EUInformation(CEFACT_NAMESPACE_URI, 4665657, LocalizedText.english("N·m/kg"), LocalizedText.english("newton metre per kilogram"));

    public static final EUInformation CODE_F47 = new EUInformation(CEFACT_NAMESPACE_URI, 4600887, LocalizedText.english("N/mm"), LocalizedText.english("newton per millimetre"));

    public static final EUInformation CODE_M93 = new EUInformation(CEFACT_NAMESPACE_URI, 5060915, LocalizedText.english("N·m/rad"), LocalizedText.english("newton metre per radian"));

    public static final EUInformation CODE_H41 = new EUInformation(CEFACT_NAMESPACE_URI, 4731953, LocalizedText.english("N·m·W⁻⁰‧⁵"), LocalizedText.english("newton metre watt to the power minus 0,5"));

    public static final EUInformation CODE_B38 = new EUInformation(CEFACT_NAMESPACE_URI, 4338488, LocalizedText.english("kgf·m"), LocalizedText.english("kilogram-force metre"));

    public static final EUInformation CODE_IA = new EUInformation(CEFACT_NAMESPACE_URI, 18753, LocalizedText.english("in·lb"), LocalizedText.english("inch pound (pound inch)"));

    public static final EUInformation CODE_4Q = new EUInformation(CEFACT_NAMESPACE_URI, 13393, LocalizedText.english("oz·in"), LocalizedText.english("ounce inch"));

    public static final EUInformation CODE_4R = new EUInformation(CEFACT_NAMESPACE_URI, 13394, LocalizedText.english("oz·ft"), LocalizedText.english("ounce foot"));

    public static final EUInformation CODE_F22 = new EUInformation(CEFACT_NAMESPACE_URI, 4600370, LocalizedText.english("lbf·ft/A"), LocalizedText.english("pound-force foot per ampere"));

    public static final EUInformation CODE_F21 = new EUInformation(CEFACT_NAMESPACE_URI, 4600369, LocalizedText.english("lbf·in"), LocalizedText.english("pound-force inch"));

    public static final EUInformation CODE_G20 = new EUInformation(CEFACT_NAMESPACE_URI, 4665904, LocalizedText.english("lbf·ft/lb"), LocalizedText.english("pound-force foot per pound"));

    public static final EUInformation CODE_J94 = new EUInformation(CEFACT_NAMESPACE_URI, 4864308, LocalizedText.english("dyn·cm"), LocalizedText.english("dyne centimetre"));

    public static final EUInformation CODE_L41 = new EUInformation(CEFACT_NAMESPACE_URI, 4994097, LocalizedText.english("ozf·in"), LocalizedText.english("ounce (avoirdupois)-force inch"));

    public static final EUInformation CODE_M92 = new EUInformation(CEFACT_NAMESPACE_URI, 5060914, LocalizedText.english("lbf·ft"), LocalizedText.english("pound-force foot"));

    public static final EUInformation CODE_M95 = new EUInformation(CEFACT_NAMESPACE_URI, 5060917, LocalizedText.english("pdl·ft"), LocalizedText.english("poundal foot"));

    public static final EUInformation CODE_M96 = new EUInformation(CEFACT_NAMESPACE_URI, 5060918, LocalizedText.english("pdl·in"), LocalizedText.english("poundal inch"));

    public static final EUInformation CODE_M97 = new EUInformation(CEFACT_NAMESPACE_URI, 5060919, LocalizedText.english("dyn·m"), LocalizedText.english("dyne metre"));

    public static final EUInformation CODE_C57 = new EUInformation(CEFACT_NAMESPACE_URI, 4404535, LocalizedText.english("N·s"), LocalizedText.english("newton second"));

    public static final EUInformation CODE_C53 = new EUInformation(CEFACT_NAMESPACE_URI, 4404531, LocalizedText.english("N·m·s"), LocalizedText.english("newton metre second"));

    public static final EUInformation CODE_74 = new EUInformation(CEFACT_NAMESPACE_URI, 14132, LocalizedText.english("mPa"), LocalizedText.english("millipascal"));

    public static final EUInformation CODE_MPA = new EUInformation(CEFACT_NAMESPACE_URI, 5066817, LocalizedText.english("MPa"), LocalizedText.english("megapascal"));

    public static final EUInformation CODE_PAL = new EUInformation(CEFACT_NAMESPACE_URI, 5259596, LocalizedText.english("Pa"), LocalizedText.english("pascal"));

    public static final EUInformation CODE_KPA = new EUInformation(CEFACT_NAMESPACE_URI, 4935745, LocalizedText.english("kPa"), LocalizedText.english("kilopascal"));

    public static final EUInformation CODE_BAR = new EUInformation(CEFACT_NAMESPACE_URI, 4342098, LocalizedText.english("bar"), LocalizedText.english("bar [unit of pressure]"));

    public static final EUInformation CODE_HBA = new EUInformation(CEFACT_NAMESPACE_URI, 4735553, LocalizedText.english("hbar"), LocalizedText.english("hectobar"));

    public static final EUInformation CODE_MBR = new EUInformation(CEFACT_NAMESPACE_URI, 5063250, LocalizedText.english("mbar"), LocalizedText.english("millibar"));

    public static final EUInformation CODE_KBA = new EUInformation(CEFACT_NAMESPACE_URI, 4932161, LocalizedText.english("kbar"), LocalizedText.english("kilobar"));

    public static final EUInformation CODE_ATM = new EUInformation(CEFACT_NAMESPACE_URI, 4281421, LocalizedText.english("atm"), LocalizedText.english("standard atmosphere"));

    public static final EUInformation CODE_A89 = new EUInformation(CEFACT_NAMESPACE_URI, 4274233, LocalizedText.english("GPa"), LocalizedText.english("gigapascal"));

    public static final EUInformation CODE_B96 = new EUInformation(CEFACT_NAMESPACE_URI, 4340022, LocalizedText.english("µPa"), LocalizedText.english("micropascal"));

    public static final EUInformation CODE_A97 = new EUInformation(CEFACT_NAMESPACE_URI, 4274487, LocalizedText.english("hPa"), LocalizedText.english("hectopascal"));

    public static final EUInformation CODE_H75 = new EUInformation(CEFACT_NAMESPACE_URI, 4732725, LocalizedText.english("daPa"), LocalizedText.english("decapascal"));

    public static final EUInformation CODE_B85 = new EUInformation(CEFACT_NAMESPACE_URI, 4339765, LocalizedText.english("µbar"), LocalizedText.english("microbar"));

    public static final EUInformation CODE_C55 = new EUInformation(CEFACT_NAMESPACE_URI, 4404533, LocalizedText.english("N/m²"), LocalizedText.english("newton per square metre"));

    public static final EUInformation CODE_C56 = new EUInformation(CEFACT_NAMESPACE_URI, 4404534, LocalizedText.english("N/mm²"), LocalizedText.english("newton per square millimetre"));

    public static final EUInformation CODE_H07 = new EUInformation(CEFACT_NAMESPACE_URI, 4730935, LocalizedText.english("Pa·s/bar"), LocalizedText.english("pascal second per bar"));

    public static final EUInformation CODE_F94 = new EUInformation(CEFACT_NAMESPACE_URI, 4602164, LocalizedText.english("hPa·m³/s"), LocalizedText.english("hectopascal cubic metre per second"));

    public static final EUInformation CODE_F93 = new EUInformation(CEFACT_NAMESPACE_URI, 4602163, LocalizedText.english("hPa·l/s"), LocalizedText.english("hectopascal litre per second"));

    public static final EUInformation CODE_F82 = new EUInformation(CEFACT_NAMESPACE_URI, 4601906, LocalizedText.english("hPa/K"), LocalizedText.english("hectopascal per kelvin"));

    public static final EUInformation CODE_F83 = new EUInformation(CEFACT_NAMESPACE_URI, 4601907, LocalizedText.english("kPa/K"), LocalizedText.english("kilopascal per kelvin"));

    public static final EUInformation CODE_F98 = new EUInformation(CEFACT_NAMESPACE_URI, 4602168, LocalizedText.english("MPa·m³/s"), LocalizedText.english("megapascal cubic metre per second"));

    public static final EUInformation CODE_F97 = new EUInformation(CEFACT_NAMESPACE_URI, 4602167, LocalizedText.english("MPa·l/s"), LocalizedText.english("megapascal litre per second"));

    public static final EUInformation CODE_F85 = new EUInformation(CEFACT_NAMESPACE_URI, 4601909, LocalizedText.english("MPa/K"), LocalizedText.english("megapascal per kelvin"));

    public static final EUInformation CODE_F96 = new EUInformation(CEFACT_NAMESPACE_URI, 4602166, LocalizedText.english("mbar·m³/s"), LocalizedText.english("millibar cubic metre per second"));

    public static final EUInformation CODE_F95 = new EUInformation(CEFACT_NAMESPACE_URI, 4602165, LocalizedText.english("mbar·l/s"), LocalizedText.english("millibar litre per second"));

    public static final EUInformation CODE_F84 = new EUInformation(CEFACT_NAMESPACE_URI, 4601908, LocalizedText.english("mbar/K"), LocalizedText.english("millibar per kelvin"));

    public static final EUInformation CODE_G01 = new EUInformation(CEFACT_NAMESPACE_URI, 4665393, LocalizedText.english("Pa·m³/s"), LocalizedText.english("pascal cubic metre per second"));

    public static final EUInformation CODE_F99 = new EUInformation(CEFACT_NAMESPACE_URI, 4602169, LocalizedText.english("Pa·l/s"), LocalizedText.english("pascal litre per second"));

    public static final EUInformation CODE_F77 = new EUInformation(CEFACT_NAMESPACE_URI, 4601655, LocalizedText.english("Pa.s/K"), LocalizedText.english("pascal second per kelvin"));

    public static final EUInformation CODE_E01 = new EUInformation(CEFACT_NAMESPACE_URI, 4534321, LocalizedText.english("N/cm²"), LocalizedText.english("newton per square centimetre"));

    public static final EUInformation CODE_FP = new EUInformation(CEFACT_NAMESPACE_URI, 18000, LocalizedText.english("lb/ft²"), LocalizedText.english("pound per square foot"));

    public static final EUInformation CODE_PS = new EUInformation(CEFACT_NAMESPACE_URI, 20563, LocalizedText.english("lbf/in²"), LocalizedText.english("pound-force per square inch"));

    public static final EUInformation CODE_B40 = new EUInformation(CEFACT_NAMESPACE_URI, 4338736, LocalizedText.english("kgf/m²"), LocalizedText.english("kilogram-force per square metre"));

    public static final EUInformation CODE_UA = new EUInformation(CEFACT_NAMESPACE_URI, 21825, LocalizedText.english("Torr"), LocalizedText.english("torr"));

    public static final EUInformation CODE_ATT = new EUInformation(CEFACT_NAMESPACE_URI, 4281428, LocalizedText.english("at"), LocalizedText.english("technical atmosphere"));

    public static final EUInformation CODE_80 = new EUInformation(CEFACT_NAMESPACE_URI, 14384, LocalizedText.english("lb/in²"), LocalizedText.english("pound per square inch absolute"));

    public static final EUInformation CODE_H78 = new EUInformation(CEFACT_NAMESPACE_URI, 4732728, LocalizedText.english("cm H₂O"), LocalizedText.english("conventional centimetre of water"));

    public static final EUInformation CODE_HP = new EUInformation(CEFACT_NAMESPACE_URI, 18512, LocalizedText.english("mm H₂O"), LocalizedText.english("conventional millimetre of water"));

    public static final EUInformation CODE_HN = new EUInformation(CEFACT_NAMESPACE_URI, 18510, LocalizedText.english("mm Hg"), LocalizedText.english("conventional millimetre of mercury"));

    public static final EUInformation CODE_F79 = new EUInformation(CEFACT_NAMESPACE_URI, 4601657, LocalizedText.english("inHg"), LocalizedText.english("inch of mercury"));

    public static final EUInformation CODE_F78 = new EUInformation(CEFACT_NAMESPACE_URI, 4601656, LocalizedText.english("inH₂O"), LocalizedText.english("inch of water"));

    public static final EUInformation CODE_J89 = new EUInformation(CEFACT_NAMESPACE_URI, 4864057, LocalizedText.english("cm Hg"), LocalizedText.english("centimetre of mercury"));

    public static final EUInformation CODE_K24 = new EUInformation(CEFACT_NAMESPACE_URI, 4928052, LocalizedText.english("ft H₂O"), LocalizedText.english("foot of water"));

    public static final EUInformation CODE_K25 = new EUInformation(CEFACT_NAMESPACE_URI, 4928053, LocalizedText.english("ft Hg"), LocalizedText.english("foot of mercury"));

    public static final EUInformation CODE_K31 = new EUInformation(CEFACT_NAMESPACE_URI, 4928305, LocalizedText.english("gf/cm²"), LocalizedText.english("gram-force per square centimetre"));

    public static final EUInformation CODE_E42 = new EUInformation(CEFACT_NAMESPACE_URI, 4535346, LocalizedText.english("kgf/cm²"), LocalizedText.english("kilogram-force per square centimetre"));

    public static final EUInformation CODE_E41 = new EUInformation(CEFACT_NAMESPACE_URI, 4535345, LocalizedText.english("kgf·m/cm²"), LocalizedText.english("kilogram-force per square millimetre"));

    public static final EUInformation CODE_K85 = new EUInformation(CEFACT_NAMESPACE_URI, 4929589, LocalizedText.english("lbf/ft²"), LocalizedText.english("pound-force per square foot"));

    public static final EUInformation CODE_K86 = new EUInformation(CEFACT_NAMESPACE_URI, 4929590, LocalizedText.english("psi/°F"), LocalizedText.english("pound-force per square inch degree Fahrenheit"));

    public static final EUInformation CODE_84 = new EUInformation(CEFACT_NAMESPACE_URI, 14388, LocalizedText.english("klbf/in²"), LocalizedText.english("A unit of pressure defining the number of kilopounds force per square inch.Use kip per square inch (common code N20)."));

    public static final EUInformation CODE_N13 = new EUInformation(CEFACT_NAMESPACE_URI, 5124403, LocalizedText.english("cmHg (0 ºC)"), LocalizedText.english("centimetre of mercury (0 ºC)"));

    public static final EUInformation CODE_N14 = new EUInformation(CEFACT_NAMESPACE_URI, 5124404, LocalizedText.english("cmH₂O (4 °C)"), LocalizedText.english("centimetre of water (4 ºC)"));

    public static final EUInformation CODE_N15 = new EUInformation(CEFACT_NAMESPACE_URI, 5124405, LocalizedText.english("ftH₂O (39,2 ºF)"), LocalizedText.english("foot of water (39.2 ºF)"));

    public static final EUInformation CODE_N16 = new EUInformation(CEFACT_NAMESPACE_URI, 5124406, LocalizedText.english("inHG (32 ºF)"), LocalizedText.english("inch of mercury (32 ºF)"));

    public static final EUInformation CODE_N17 = new EUInformation(CEFACT_NAMESPACE_URI, 5124407, LocalizedText.english("inHg (60 ºF)"), LocalizedText.english("inch of mercury (60 ºF)"));

    public static final EUInformation CODE_N18 = new EUInformation(CEFACT_NAMESPACE_URI, 5124408, LocalizedText.english("inH₂O (39,2 ºF)"), LocalizedText.english("inch of water (39.2 ºF)"));

    public static final EUInformation CODE_N19 = new EUInformation(CEFACT_NAMESPACE_URI, 5124409, LocalizedText.english("inH₂O (60 ºF)"), LocalizedText.english("inch of water (60 ºF)"));

    public static final EUInformation CODE_N20 = new EUInformation(CEFACT_NAMESPACE_URI, 5124656, LocalizedText.english("ksi"), LocalizedText.english("kip per square inch"));

    public static final EUInformation CODE_N21 = new EUInformation(CEFACT_NAMESPACE_URI, 5124657, LocalizedText.english("pdl/ft²"), LocalizedText.english("poundal per square foot"));

    public static final EUInformation CODE_N22 = new EUInformation(CEFACT_NAMESPACE_URI, 5124658, LocalizedText.english("oz/in²"), LocalizedText.english("ounce (avoirdupois) per square inch"));

    public static final EUInformation CODE_N23 = new EUInformation(CEFACT_NAMESPACE_URI, 5124659, LocalizedText.english("mH₂O"), LocalizedText.english("conventional metre of water"));

    public static final EUInformation CODE_N24 = new EUInformation(CEFACT_NAMESPACE_URI, 5124660, LocalizedText.english("g/mm²"), LocalizedText.english("gram per square millimetre"));

    public static final EUInformation CODE_N25 = new EUInformation(CEFACT_NAMESPACE_URI, 5124661, LocalizedText.english("lb/yd²"), LocalizedText.english("pound per square yard"));

    public static final EUInformation CODE_N26 = new EUInformation(CEFACT_NAMESPACE_URI, 5124662, LocalizedText.english("pdl/in²"), LocalizedText.english("poundal per square inch"));

    public static final EUInformation CODE_E99 = new EUInformation(CEFACT_NAMESPACE_URI, 4536633, LocalizedText.english("hPa/bar"), LocalizedText.english("hectopascal per bar"));

    public static final EUInformation CODE_F05 = new EUInformation(CEFACT_NAMESPACE_URI, 4599861, LocalizedText.english("MPa/bar"), LocalizedText.english("megapascal per bar"));

    public static final EUInformation CODE_F04 = new EUInformation(CEFACT_NAMESPACE_URI, 4599860, LocalizedText.english("mbar/bar"), LocalizedText.english("millibar per bar"));

    public static final EUInformation CODE_F07 = new EUInformation(CEFACT_NAMESPACE_URI, 4599863, LocalizedText.english("Pa/bar"), LocalizedText.english("pascal per bar"));

    public static final EUInformation CODE_F03 = new EUInformation(CEFACT_NAMESPACE_URI, 4599859, LocalizedText.english("kPa/bar"), LocalizedText.english("kilopascal per bar"));

    public static final EUInformation CODE_L52 = new EUInformation(CEFACT_NAMESPACE_URI, 4994354, LocalizedText.english("psi/psi"), LocalizedText.english("psi per psi"));

    public static final EUInformation CODE_J56 = new EUInformation(CEFACT_NAMESPACE_URI, 4863286, LocalizedText.english("bar/bar"), LocalizedText.english("bar per bar"));

    public static final EUInformation CODE_C96 = new EUInformation(CEFACT_NAMESPACE_URI, 4405558, LocalizedText.english("Pa⁻¹"), LocalizedText.english("reciprocal pascal or pascal to the power minus one"));

    public static final EUInformation CODE_F58 = new EUInformation(CEFACT_NAMESPACE_URI, 4601144, LocalizedText.english("1/bar"), LocalizedText.english("reciprocal bar"));

    public static final EUInformation CODE_B83 = new EUInformation(CEFACT_NAMESPACE_URI, 4339763, LocalizedText.english("m⁴"), LocalizedText.english("metre to the fourth power"));

    public static final EUInformation CODE_G77 = new EUInformation(CEFACT_NAMESPACE_URI, 4667191, LocalizedText.english("mm⁴"), LocalizedText.english("millimetre to the fourth power"));

    public static final EUInformation CODE_D69 = new EUInformation(CEFACT_NAMESPACE_URI, 4470329, LocalizedText.english("in⁴"), LocalizedText.english("inch to the fourth power"));

    public static final EUInformation CODE_N27 = new EUInformation(CEFACT_NAMESPACE_URI, 5124663, LocalizedText.english("ft⁴"), LocalizedText.english("foot to the fourth power"));

    public static final EUInformation CODE_C65 = new EUInformation(CEFACT_NAMESPACE_URI, 4404789, LocalizedText.english("Pa·s"), LocalizedText.english("pascal second"));

    public static final EUInformation CODE_N37 = new EUInformation(CEFACT_NAMESPACE_URI, 5124919, LocalizedText.english("kg/(m·s)"), LocalizedText.english("kilogram per metre second"));

    public static final EUInformation CODE_N38 = new EUInformation(CEFACT_NAMESPACE_URI, 5124920, LocalizedText.english("kg/(m·min)"), LocalizedText.english("kilogram per metre minute"));

    public static final EUInformation CODE_C24 = new EUInformation(CEFACT_NAMESPACE_URI, 4403764, LocalizedText.english("mPa·s"), LocalizedText.english("millipascal second"));

    public static final EUInformation CODE_N36 = new EUInformation(CEFACT_NAMESPACE_URI, 5124918, LocalizedText.english("(N/m²)·s"), LocalizedText.english("newton second per square metre"));

    public static final EUInformation CODE_N39 = new EUInformation(CEFACT_NAMESPACE_URI, 5124921, LocalizedText.english("kg/(m·d)"), LocalizedText.english("kilogram per metre day"));

    public static final EUInformation CODE_N40 = new EUInformation(CEFACT_NAMESPACE_URI, 5125168, LocalizedText.english("kg/(m·h)"), LocalizedText.english("kilogram per metre hour"));

    public static final EUInformation CODE_N41 = new EUInformation(CEFACT_NAMESPACE_URI, 5125169, LocalizedText.english("g/(cm·s)"), LocalizedText.english("gram per centimetre second"));

    public static final EUInformation CODE_89 = new EUInformation(CEFACT_NAMESPACE_URI, 14393, LocalizedText.english("P"), LocalizedText.english("poise"));

    public static final EUInformation CODE_C7 = new EUInformation(CEFACT_NAMESPACE_URI, 17207, LocalizedText.english("cP"), LocalizedText.english("centipoise"));

    public static final EUInformation CODE_F06 = new EUInformation(CEFACT_NAMESPACE_URI, 4599862, LocalizedText.english("P/bar"), LocalizedText.english("poise per bar"));

    public static final EUInformation CODE_F86 = new EUInformation(CEFACT_NAMESPACE_URI, 4601910, LocalizedText.english("P/K"), LocalizedText.english("poise per kelvin"));

    public static final EUInformation CODE_J32 = new EUInformation(CEFACT_NAMESPACE_URI, 4862770, LocalizedText.english("µP"), LocalizedText.english("micropoise"));

    public static final EUInformation CODE_J73 = new EUInformation(CEFACT_NAMESPACE_URI, 4863795, LocalizedText.english("cP/K"), LocalizedText.english("centipoise per kelvin"));

    public static final EUInformation CODE_J74 = new EUInformation(CEFACT_NAMESPACE_URI, 4863796, LocalizedText.english("cP/bar"), LocalizedText.english("centipoise per bar"));

    public static final EUInformation CODE_K67 = new EUInformation(CEFACT_NAMESPACE_URI, 4929079, LocalizedText.english("lb/(ft·h)"), LocalizedText.english("pound per foot hour"));

    public static final EUInformation CODE_K68 = new EUInformation(CEFACT_NAMESPACE_URI, 4929080, LocalizedText.english("lb/(ft·s)"), LocalizedText.english("pound per foot second"));

    public static final EUInformation CODE_K91 = new EUInformation(CEFACT_NAMESPACE_URI, 4929841, LocalizedText.english("lbf·s/ft²"), LocalizedText.english("pound-force second per square foot"));

    public static final EUInformation CODE_K92 = new EUInformation(CEFACT_NAMESPACE_URI, 4929842, LocalizedText.english("lbf·s/in²"), LocalizedText.english("pound-force second per square inch"));

    public static final EUInformation CODE_L15 = new EUInformation(CEFACT_NAMESPACE_URI, 4993333, LocalizedText.english("mPa·s/K"), LocalizedText.english("millipascal second per kelvin"));

    public static final EUInformation CODE_L16 = new EUInformation(CEFACT_NAMESPACE_URI, 4993334, LocalizedText.english("mPa·s/bar"), LocalizedText.english("millipascal second per bar"));

    public static final EUInformation CODE_L64 = new EUInformation(CEFACT_NAMESPACE_URI, 4994612, LocalizedText.english("slug/(ft·s)"), LocalizedText.english("slug per foot second"));

    public static final EUInformation CODE_N34 = new EUInformation(CEFACT_NAMESPACE_URI, 5124916, LocalizedText.english("(pdl/ft²)·s"), LocalizedText.english("poundal second per square foot"));

    public static final EUInformation CODE_N35 = new EUInformation(CEFACT_NAMESPACE_URI, 5124917, LocalizedText.english("P/Pa"), LocalizedText.english("poise per pascal"));

    public static final EUInformation CODE_N42 = new EUInformation(CEFACT_NAMESPACE_URI, 5125170, LocalizedText.english("(pdl/in²)·s"), LocalizedText.english("poundal second per square inch"));

    public static final EUInformation CODE_N43 = new EUInformation(CEFACT_NAMESPACE_URI, 5125171, LocalizedText.english("lb/(ft·min)"), LocalizedText.english("pound per foot minute"));

    public static final EUInformation CODE_N44 = new EUInformation(CEFACT_NAMESPACE_URI, 5125172, LocalizedText.english("lb/(ft·d)"), LocalizedText.english("pound per foot day"));

    public static final EUInformation CODE_S4 = new EUInformation(CEFACT_NAMESPACE_URI, 21300, LocalizedText.english("m²/s"), LocalizedText.english("square metre per second"));

    public static final EUInformation CODE_M82 = new EUInformation(CEFACT_NAMESPACE_URI, 5060658, LocalizedText.english("(m²/s)/Pa"), LocalizedText.english("square metre per second pascal"));

    public static final EUInformation CODE_C17 = new EUInformation(CEFACT_NAMESPACE_URI, 4403511, LocalizedText.english("mm²/s"), LocalizedText.english("millimetre squared per second"));

    public static final EUInformation CODE_G41 = new EUInformation(CEFACT_NAMESPACE_URI, 4666417, LocalizedText.english("m²/(s·bar)"), LocalizedText.english("square metre per second bar"));

    public static final EUInformation CODE_G09 = new EUInformation(CEFACT_NAMESPACE_URI, 4665401, LocalizedText.english("m²/(s·K)"), LocalizedText.english("square metre per second kelvin"));

    public static final EUInformation CODE_91 = new EUInformation(CEFACT_NAMESPACE_URI, 14641, LocalizedText.english("St"), LocalizedText.english("stokes"));

    public static final EUInformation CODE_4C = new EUInformation(CEFACT_NAMESPACE_URI, 13379, LocalizedText.english("cSt"), LocalizedText.english("centistokes"));

    public static final EUInformation CODE_G46 = new EUInformation(CEFACT_NAMESPACE_URI, 4666422, LocalizedText.english("St/bar"), LocalizedText.english("stokes per bar"));

    public static final EUInformation CODE_G10 = new EUInformation(CEFACT_NAMESPACE_URI, 4665648, LocalizedText.english("St/K"), LocalizedText.english("stokes per kelvin"));

    public static final EUInformation CODE_S3 = new EUInformation(CEFACT_NAMESPACE_URI, 21299, LocalizedText.english("ft²/s"), LocalizedText.english("square foot per second"));

    public static final EUInformation CODE_G08 = new EUInformation(CEFACT_NAMESPACE_URI, 4665400, LocalizedText.english("in²/s"), LocalizedText.english("square inch per second"));

    public static final EUInformation CODE_M79 = new EUInformation(CEFACT_NAMESPACE_URI, 5060409, LocalizedText.english("ft²/h"), LocalizedText.english("square foot per hour"));

    public static final EUInformation CODE_M80 = new EUInformation(CEFACT_NAMESPACE_URI, 5060656, LocalizedText.english("St/Pa"), LocalizedText.english("stokes per pascal"));

    public static final EUInformation CODE_M81 = new EUInformation(CEFACT_NAMESPACE_URI, 5060657, LocalizedText.english("cm²/s"), LocalizedText.english("square centimetre per second"));

    public static final EUInformation CODE_4P = new EUInformation(CEFACT_NAMESPACE_URI, 13392, LocalizedText.english("N/m"), LocalizedText.english("newton per metre"));

    public static final EUInformation CODE_C22 = new EUInformation(CEFACT_NAMESPACE_URI, 4403762, LocalizedText.english("mN/m"), LocalizedText.english("millinewton per metre"));

    public static final EUInformation CODE_M23 = new EUInformation(CEFACT_NAMESPACE_URI, 5059123, LocalizedText.english("N/cm"), LocalizedText.english("newton per centimetre"));

    public static final EUInformation CODE_N31 = new EUInformation(CEFACT_NAMESPACE_URI, 5124913, LocalizedText.english("kN/m"), LocalizedText.english("kilonewton per metre"));

    public static final EUInformation CODE_DX = new EUInformation(CEFACT_NAMESPACE_URI, 17496, LocalizedText.english("dyn/cm"), LocalizedText.english("dyne per centimetre"));

    public static final EUInformation CODE_N32 = new EUInformation(CEFACT_NAMESPACE_URI, 5124914, LocalizedText.english("pdl/in"), LocalizedText.english("poundal per inch"));

    public static final EUInformation CODE_N33 = new EUInformation(CEFACT_NAMESPACE_URI, 5124915, LocalizedText.english("lbf/yd"), LocalizedText.english("pound-force per yard"));

    public static final EUInformation CODE_M34 = new EUInformation(CEFACT_NAMESPACE_URI, 5059380, LocalizedText.english("N·m/m²"), LocalizedText.english("newton metre per square metre"));

    public static final EUInformation CODE_JOU = new EUInformation(CEFACT_NAMESPACE_URI, 4869973, LocalizedText.english("J"), LocalizedText.english("joule"));

    public static final EUInformation CODE_KJO = new EUInformation(CEFACT_NAMESPACE_URI, 4934223, LocalizedText.english("kJ"), LocalizedText.english("kilojoule"));

    public static final EUInformation CODE_A68 = new EUInformation(CEFACT_NAMESPACE_URI, 4273720, LocalizedText.english("EJ"), LocalizedText.english("exajoule"));

    public static final EUInformation CODE_C68 = new EUInformation(CEFACT_NAMESPACE_URI, 4404792, LocalizedText.english("PJ"), LocalizedText.english("petajoule"));

    public static final EUInformation CODE_D30 = new EUInformation(CEFACT_NAMESPACE_URI, 4469552, LocalizedText.english("TJ"), LocalizedText.english("terajoule"));

    public static final EUInformation CODE_GV = new EUInformation(CEFACT_NAMESPACE_URI, 18262, LocalizedText.english("GJ"), LocalizedText.english("gigajoule"));

    public static final EUInformation CODE_3B = new EUInformation(CEFACT_NAMESPACE_URI, 13122, LocalizedText.english("MJ"), LocalizedText.english("megajoule"));

    public static final EUInformation CODE_C15 = new EUInformation(CEFACT_NAMESPACE_URI, 4403509, LocalizedText.english("mJ"), LocalizedText.english("millijoule"));

    public static final EUInformation CODE_A70 = new EUInformation(CEFACT_NAMESPACE_URI, 4273968, LocalizedText.english("fJ"), LocalizedText.english("femtojoule"));

    public static final EUInformation CODE_A13 = new EUInformation(CEFACT_NAMESPACE_URI, 4272435, LocalizedText.english("aJ"), LocalizedText.english("attojoule"));

    public static final EUInformation CODE_WHR = new EUInformation(CEFACT_NAMESPACE_URI, 5720146, LocalizedText.english("W·h"), LocalizedText.english("watt hour"));

    public static final EUInformation CODE_MWH = new EUInformation(CEFACT_NAMESPACE_URI, 5068616, LocalizedText.english("MW·h"), LocalizedText.english("megawatt hour (1000 kW.h)"));

    public static final EUInformation CODE_KWH = new EUInformation(CEFACT_NAMESPACE_URI, 4937544, LocalizedText.english("kW·h"), LocalizedText.english("kilowatt hour"));

    public static final EUInformation CODE_GWH = new EUInformation(CEFACT_NAMESPACE_URI, 4675400, LocalizedText.english("GW·h"), LocalizedText.english("gigawatt hour"));

    public static final EUInformation CODE_D32 = new EUInformation(CEFACT_NAMESPACE_URI, 4469554, LocalizedText.english("TW·h"), LocalizedText.english("terawatt hour"));

    public static final EUInformation CODE_A53 = new EUInformation(CEFACT_NAMESPACE_URI, 4273459, LocalizedText.english("eV"), LocalizedText.english("electronvolt"));

    public static final EUInformation CODE_B71 = new EUInformation(CEFACT_NAMESPACE_URI, 4339505, LocalizedText.english("MeV"), LocalizedText.english("megaelectronvolt"));

    public static final EUInformation CODE_A85 = new EUInformation(CEFACT_NAMESPACE_URI, 4274229, LocalizedText.english("GeV"), LocalizedText.english("gigaelectronvolt"));

    public static final EUInformation CODE_B29 = new EUInformation(CEFACT_NAMESPACE_URI, 4338233, LocalizedText.english("keV"), LocalizedText.english("kiloelectronvolt"));

    public static final EUInformation CODE_A57 = new EUInformation(CEFACT_NAMESPACE_URI, 4273463, LocalizedText.english("erg"), LocalizedText.english("erg"));

    public static final EUInformation CODE_85 = new EUInformation(CEFACT_NAMESPACE_URI, 14389, LocalizedText.english("ft·lbf"), LocalizedText.english("foot pound-force"));

    public static final EUInformation CODE_N46 = new EUInformation(CEFACT_NAMESPACE_URI, 5125174, LocalizedText.english("ft·pdl"), LocalizedText.english("foot poundal"));

    public static final EUInformation CODE_N47 = new EUInformation(CEFACT_NAMESPACE_URI, 5125175, LocalizedText.english("in·pdl"), LocalizedText.english("inch poundal"));

    public static final EUInformation CODE_WTT = new EUInformation(CEFACT_NAMESPACE_URI, 5723220, LocalizedText.english("W"), LocalizedText.english("watt"));

    public static final EUInformation CODE_KWT = new EUInformation(CEFACT_NAMESPACE_URI, 4937556, LocalizedText.english("kW"), LocalizedText.english("kilowatt"));

    public static final EUInformation CODE_MAW = new EUInformation(CEFACT_NAMESPACE_URI, 5062999, LocalizedText.english("MW"), LocalizedText.english("megawatt"));

    public static final EUInformation CODE_A90 = new EUInformation(CEFACT_NAMESPACE_URI, 4274480, LocalizedText.english("GW"), LocalizedText.english("gigawatt"));

    public static final EUInformation CODE_C31 = new EUInformation(CEFACT_NAMESPACE_URI, 4404017, LocalizedText.english("mW"), LocalizedText.english("milliwatt"));

    public static final EUInformation CODE_D80 = new EUInformation(CEFACT_NAMESPACE_URI, 4470832, LocalizedText.english("µW"), LocalizedText.english("microwatt"));

    public static final EUInformation CODE_A63 = new EUInformation(CEFACT_NAMESPACE_URI, 4273715, LocalizedText.english("erg/s"), LocalizedText.english("erg per second"));

    public static final EUInformation CODE_A74 = new EUInformation(CEFACT_NAMESPACE_URI, 4273972, LocalizedText.english("ft·lbf/s"), LocalizedText.english("foot pound-force per second"));

    public static final EUInformation CODE_B39 = new EUInformation(CEFACT_NAMESPACE_URI, 4338489, LocalizedText.english("kgf·m/s"), LocalizedText.english("kilogram-force metre per second"));

    public static final EUInformation CODE_HJ = new EUInformation(CEFACT_NAMESPACE_URI, 18506, LocalizedText.english("metric hp"), LocalizedText.english("metric horse power"));

    public static final EUInformation CODE_A25 = new EUInformation(CEFACT_NAMESPACE_URI, 4272693, LocalizedText.english("CV"), LocalizedText.english("cheval vapeur"));

    public static final EUInformation CODE_BHP = new EUInformation(CEFACT_NAMESPACE_URI, 4343888, LocalizedText.english("BHP"), LocalizedText.english("brake horse power"));

    public static final EUInformation CODE_K15 = new EUInformation(CEFACT_NAMESPACE_URI, 4927797, LocalizedText.english("ft·lbf/h"), LocalizedText.english("foot pound-force per hour"));

    public static final EUInformation CODE_K16 = new EUInformation(CEFACT_NAMESPACE_URI, 4927798, LocalizedText.english("ft·lbf/min"), LocalizedText.english("foot pound-force per minute"));

    public static final EUInformation CODE_K42 = new EUInformation(CEFACT_NAMESPACE_URI, 4928562, LocalizedText.english("boiler hp"), LocalizedText.english("horsepower (boiler)"));

    public static final EUInformation CODE_N12 = new EUInformation(CEFACT_NAMESPACE_URI, 5124402, LocalizedText.english("PS"), LocalizedText.english("Pferdestaerke"));

    public static final EUInformation CODE_KGS = new EUInformation(CEFACT_NAMESPACE_URI, 4933459, LocalizedText.english("kg/s"), LocalizedText.english("kilogram per second"));

    public static final EUInformation CODE_H56 = new EUInformation(CEFACT_NAMESPACE_URI, 4732214, LocalizedText.english("kg/(m²·s)"), LocalizedText.english("kilogram per square metre second"));

    public static final EUInformation CODE_M87 = new EUInformation(CEFACT_NAMESPACE_URI, 5060663, LocalizedText.english("(kg/s)/Pa"), LocalizedText.english("kilogram per second pascal"));

    public static final EUInformation CODE_4M = new EUInformation(CEFACT_NAMESPACE_URI, 13389, LocalizedText.english("mg/h"), LocalizedText.english("milligram per hour"));

    public static final EUInformation CODE_F26 = new EUInformation(CEFACT_NAMESPACE_URI, 4600374, LocalizedText.english("g/d"), LocalizedText.english("gram per day"));

    public static final EUInformation CODE_F62 = new EUInformation(CEFACT_NAMESPACE_URI, 4601394, LocalizedText.english("g/(d·bar)"), LocalizedText.english("gram per day bar"));

    public static final EUInformation CODE_F35 = new EUInformation(CEFACT_NAMESPACE_URI, 4600629, LocalizedText.english("g/(d·K)"), LocalizedText.english("gram per day kelvin"));

    public static final EUInformation CODE_F27 = new EUInformation(CEFACT_NAMESPACE_URI, 4600375, LocalizedText.english("g/h"), LocalizedText.english("gram per hour"));

    public static final EUInformation CODE_F63 = new EUInformation(CEFACT_NAMESPACE_URI, 4601395, LocalizedText.english("g/(h·bar)"), LocalizedText.english("gram per hour bar"));

    public static final EUInformation CODE_F36 = new EUInformation(CEFACT_NAMESPACE_URI, 4600630, LocalizedText.english("g/(h·K)"), LocalizedText.english("gram per hour kelvin"));

    public static final EUInformation CODE_F28 = new EUInformation(CEFACT_NAMESPACE_URI, 4600376, LocalizedText.english("g/min"), LocalizedText.english("gram per minute"));

    public static final EUInformation CODE_F64 = new EUInformation(CEFACT_NAMESPACE_URI, 4601396, LocalizedText.english("g/(min·bar)"), LocalizedText.english("gram per minute bar"));

    public static final EUInformation CODE_F37 = new EUInformation(CEFACT_NAMESPACE_URI, 4600631, LocalizedText.english("g/(min·K)"), LocalizedText.english("gram per minute kelvin"));

    public static final EUInformation CODE_F29 = new EUInformation(CEFACT_NAMESPACE_URI, 4600377, LocalizedText.english("g/s"), LocalizedText.english("gram per second"));

    public static final EUInformation CODE_F65 = new EUInformation(CEFACT_NAMESPACE_URI, 4601397, LocalizedText.english("g/(s·bar)"), LocalizedText.english("gram per second bar"));

    public static final EUInformation CODE_F38 = new EUInformation(CEFACT_NAMESPACE_URI, 4600632, LocalizedText.english("g/(s·K)"), LocalizedText.english("gram per second kelvin"));

    public static final EUInformation CODE_F30 = new EUInformation(CEFACT_NAMESPACE_URI, 4600624, LocalizedText.english("kg/d"), LocalizedText.english("kilogram per day"));

    public static final EUInformation CODE_F66 = new EUInformation(CEFACT_NAMESPACE_URI, 4601398, LocalizedText.english("kg/(d·bar)"), LocalizedText.english("kilogram per day bar"));

    public static final EUInformation CODE_F39 = new EUInformation(CEFACT_NAMESPACE_URI, 4600633, LocalizedText.english("kg/(d·K)"), LocalizedText.english("kilogram per day kelvin"));

    public static final EUInformation CODE_E93 = new EUInformation(CEFACT_NAMESPACE_URI, 4536627, LocalizedText.english("kg/h"), LocalizedText.english("kilogram per hour"));

    public static final EUInformation CODE_F67 = new EUInformation(CEFACT_NAMESPACE_URI, 4601399, LocalizedText.english("kg/(h·bar)"), LocalizedText.english("kilogram per hour bar"));

    public static final EUInformation CODE_F40 = new EUInformation(CEFACT_NAMESPACE_URI, 4600880, LocalizedText.english("kg/(h·K)"), LocalizedText.english("kilogram per hour kelvin"));

    public static final EUInformation CODE_F31 = new EUInformation(CEFACT_NAMESPACE_URI, 4600625, LocalizedText.english("kg/min"), LocalizedText.english("kilogram per minute"));

    public static final EUInformation CODE_F68 = new EUInformation(CEFACT_NAMESPACE_URI, 4601400, LocalizedText.english("kg/(min·bar)"), LocalizedText.english("kilogram per minute bar"));

    public static final EUInformation CODE_F41 = new EUInformation(CEFACT_NAMESPACE_URI, 4600881, LocalizedText.english("kg/(min·K)"), LocalizedText.english("kilogram per minute kelvin"));

    public static final EUInformation CODE_F69 = new EUInformation(CEFACT_NAMESPACE_URI, 4601401, LocalizedText.english("kg/(s·bar)"), LocalizedText.english("kilogram per second bar"));

    public static final EUInformation CODE_F42 = new EUInformation(CEFACT_NAMESPACE_URI, 4600882, LocalizedText.english("kg/(s·K)"), LocalizedText.english("kilogram per second kelvin"));

    public static final EUInformation CODE_F32 = new EUInformation(CEFACT_NAMESPACE_URI, 4600626, LocalizedText.english("mg/d"), LocalizedText.english("milligram per day"));

    public static final EUInformation CODE_F70 = new EUInformation(CEFACT_NAMESPACE_URI, 4601648, LocalizedText.english("mg/(d·bar)"), LocalizedText.english("milligram per day bar"));

    public static final EUInformation CODE_F43 = new EUInformation(CEFACT_NAMESPACE_URI, 4600883, LocalizedText.english("mg/(d·K)"), LocalizedText.english("milligram per day kelvin"));

    public static final EUInformation CODE_F71 = new EUInformation(CEFACT_NAMESPACE_URI, 4601649, LocalizedText.english("mg/(h·bar)"), LocalizedText.english("milligram per hour bar"));

    public static final EUInformation CODE_F44 = new EUInformation(CEFACT_NAMESPACE_URI, 4600884, LocalizedText.english("mg/(h·K)"), LocalizedText.english("milligram per hour kelvin"));

    public static final EUInformation CODE_F33 = new EUInformation(CEFACT_NAMESPACE_URI, 4600627, LocalizedText.english("mg/min"), LocalizedText.english("milligram per minute"));

    public static final EUInformation CODE_F72 = new EUInformation(CEFACT_NAMESPACE_URI, 4601650, LocalizedText.english("mg/(min·bar)"), LocalizedText.english("milligram per minute bar"));

    public static final EUInformation CODE_F45 = new EUInformation(CEFACT_NAMESPACE_URI, 4600885, LocalizedText.english("mg/(min·K)"), LocalizedText.english("milligram per minute kelvin"));

    public static final EUInformation CODE_F34 = new EUInformation(CEFACT_NAMESPACE_URI, 4600628, LocalizedText.english("mg/s"), LocalizedText.english("milligram per second"));

    public static final EUInformation CODE_F73 = new EUInformation(CEFACT_NAMESPACE_URI, 4601651, LocalizedText.english("mg/(s·bar)"), LocalizedText.english("milligram per second bar"));

    public static final EUInformation CODE_F46 = new EUInformation(CEFACT_NAMESPACE_URI, 4600886, LocalizedText.english("mg/(s·K)"), LocalizedText.english("milligram per second kelvin"));

    public static final EUInformation CODE_F25 = new EUInformation(CEFACT_NAMESPACE_URI, 4600373, LocalizedText.english("g/Hz"), LocalizedText.english("gram per hertz"));

    public static final EUInformation CODE_4W = new EUInformation(CEFACT_NAMESPACE_URI, 13399, LocalizedText.english("ton (US) /h"), LocalizedText.english("ton (US) per hour"));

    public static final EUInformation CODE_4U = new EUInformation(CEFACT_NAMESPACE_URI, 13397, LocalizedText.english("lb/h"), LocalizedText.english("pound per hour"));

    public static final EUInformation CODE_K66 = new EUInformation(CEFACT_NAMESPACE_URI, 4929078, LocalizedText.english("lb/d"), LocalizedText.english("pound (avoirdupois) per day"));

    public static final EUInformation CODE_K73 = new EUInformation(CEFACT_NAMESPACE_URI, 4929331, LocalizedText.english("(lb/h)/°F"), LocalizedText.english("pound (avoirdupois) per hour degree Fahrenheit"));

    public static final EUInformation CODE_K74 = new EUInformation(CEFACT_NAMESPACE_URI, 4929332, LocalizedText.english("(lb/h)/psi"), LocalizedText.english("pound (avoirdupois) per hour psi"));

    public static final EUInformation CODE_K78 = new EUInformation(CEFACT_NAMESPACE_URI, 4929336, LocalizedText.english("lb/min"), LocalizedText.english("pound (avoirdupois) per minute"));

    public static final EUInformation CODE_K79 = new EUInformation(CEFACT_NAMESPACE_URI, 4929337, LocalizedText.english("lb/(min·°F)"), LocalizedText.english("pound (avoirdupois) per minute degree Fahrenheit"));

    public static final EUInformation CODE_K80 = new EUInformation(CEFACT_NAMESPACE_URI, 4929584, LocalizedText.english("(lb/min)/psi"), LocalizedText.english("pound (avoirdupois) per minute psi"));

    public static final EUInformation CODE_K81 = new EUInformation(CEFACT_NAMESPACE_URI, 4929585, LocalizedText.english("lb/s"), LocalizedText.english("pound (avoirdupois) per second"));

    public static final EUInformation CODE_K82 = new EUInformation(CEFACT_NAMESPACE_URI, 4929586, LocalizedText.english("(lb/s)/°F"), LocalizedText.english("pound (avoirdupois) per second degree Fahrenheit"));

    public static final EUInformation CODE_K83 = new EUInformation(CEFACT_NAMESPACE_URI, 4929587, LocalizedText.english("(lb/s)/psi"), LocalizedText.english("pound (avoirdupois) per second psi"));

    public static final EUInformation CODE_L33 = new EUInformation(CEFACT_NAMESPACE_URI, 4993843, LocalizedText.english("oz/d"), LocalizedText.english("ounce (avoirdupois) per day"));

    public static final EUInformation CODE_L34 = new EUInformation(CEFACT_NAMESPACE_URI, 4993844, LocalizedText.english("oz/h"), LocalizedText.english("ounce (avoirdupois) per hour"));

    public static final EUInformation CODE_L35 = new EUInformation(CEFACT_NAMESPACE_URI, 4993845, LocalizedText.english("oz/min"), LocalizedText.english("ounce (avoirdupois) per minute"));

    public static final EUInformation CODE_L36 = new EUInformation(CEFACT_NAMESPACE_URI, 4993846, LocalizedText.english("oz/s"), LocalizedText.english("ounce (avoirdupois) per second"));

    public static final EUInformation CODE_L63 = new EUInformation(CEFACT_NAMESPACE_URI, 4994611, LocalizedText.english("slug/d"), LocalizedText.english("slug per day"));

    public static final EUInformation CODE_L66 = new EUInformation(CEFACT_NAMESPACE_URI, 4994614, LocalizedText.english("slug/h"), LocalizedText.english("slug per hour"));

    public static final EUInformation CODE_L67 = new EUInformation(CEFACT_NAMESPACE_URI, 4994615, LocalizedText.english("slug/min"), LocalizedText.english("slug per minute"));

    public static final EUInformation CODE_L68 = new EUInformation(CEFACT_NAMESPACE_URI, 4994616, LocalizedText.english("slug/s"), LocalizedText.english("slug per second"));

    public static final EUInformation CODE_L71 = new EUInformation(CEFACT_NAMESPACE_URI, 4994865, LocalizedText.english("t/d"), LocalizedText.english("tonne per day"));

    public static final EUInformation CODE_L72 = new EUInformation(CEFACT_NAMESPACE_URI, 4994866, LocalizedText.english("(t/d)/K"), LocalizedText.english("tonne per day kelvin"));

    public static final EUInformation CODE_L73 = new EUInformation(CEFACT_NAMESPACE_URI, 4994867, LocalizedText.english("(t/d)/bar"), LocalizedText.english("tonne per day bar"));

    public static final EUInformation CODE_E18 = new EUInformation(CEFACT_NAMESPACE_URI, 4534584, LocalizedText.english("t/h"), LocalizedText.english("tonne per hour"));

    public static final EUInformation CODE_L74 = new EUInformation(CEFACT_NAMESPACE_URI, 4994868, LocalizedText.english("(t/h)/K"), LocalizedText.english("tonne per hour kelvin"));

    public static final EUInformation CODE_L75 = new EUInformation(CEFACT_NAMESPACE_URI, 4994869, LocalizedText.english("(t/h)/bar"), LocalizedText.english("tonne per hour bar"));

    public static final EUInformation CODE_L78 = new EUInformation(CEFACT_NAMESPACE_URI, 4994872, LocalizedText.english("t/min"), LocalizedText.english("tonne per minute"));

    public static final EUInformation CODE_L79 = new EUInformation(CEFACT_NAMESPACE_URI, 4994873, LocalizedText.english("(t/min)/K"), LocalizedText.english("tonne per minute kelvin"));

    public static final EUInformation CODE_L80 = new EUInformation(CEFACT_NAMESPACE_URI, 4995120, LocalizedText.english("(t/min)/bar"), LocalizedText.english("tonne per minute bar"));

    public static final EUInformation CODE_L81 = new EUInformation(CEFACT_NAMESPACE_URI, 4995121, LocalizedText.english("t/s"), LocalizedText.english("tonne per second"));

    public static final EUInformation CODE_L82 = new EUInformation(CEFACT_NAMESPACE_URI, 4995122, LocalizedText.english("(t/s)/K"), LocalizedText.english("tonne per second kelvin"));

    public static final EUInformation CODE_L83 = new EUInformation(CEFACT_NAMESPACE_URI, 4995123, LocalizedText.english("(t/s)/bar"), LocalizedText.english("tonne per second bar"));

    public static final EUInformation CODE_L85 = new EUInformation(CEFACT_NAMESPACE_URI, 4995125, LocalizedText.english("ton (UK)/d"), LocalizedText.english("ton long per day"));

    public static final EUInformation CODE_L88 = new EUInformation(CEFACT_NAMESPACE_URI, 4995128, LocalizedText.english("ton (US)/d"), LocalizedText.english("ton short per day"));

    public static final EUInformation CODE_L89 = new EUInformation(CEFACT_NAMESPACE_URI, 4995129, LocalizedText.english("ton (US)/(h·°F)"), LocalizedText.english("ton short per hour degree Fahrenheit"));

    public static final EUInformation CODE_L90 = new EUInformation(CEFACT_NAMESPACE_URI, 4995376, LocalizedText.english("(ton (US)/h)/psi"), LocalizedText.english("ton short per hour psi"));

    public static final EUInformation CODE_M88 = new EUInformation(CEFACT_NAMESPACE_URI, 5060664, LocalizedText.english("t/mo"), LocalizedText.english("tonne per month"));

    public static final EUInformation CODE_M89 = new EUInformation(CEFACT_NAMESPACE_URI, 5060665, LocalizedText.english("t/y"), LocalizedText.english("tonne per year"));

    public static final EUInformation CODE_M90 = new EUInformation(CEFACT_NAMESPACE_URI, 5060912, LocalizedText.english("klb/h"), LocalizedText.english("kilopound per hour"));

    public static final EUInformation CODE_J33 = new EUInformation(CEFACT_NAMESPACE_URI, 4862771, LocalizedText.english("µg/kg"), LocalizedText.english("microgram per kilogram"));

    public static final EUInformation CODE_L32 = new EUInformation(CEFACT_NAMESPACE_URI, 4993842, LocalizedText.english("ng/kg"), LocalizedText.english("nanogram per kilogram"));

    public static final EUInformation CODE_NA = new EUInformation(CEFACT_NAMESPACE_URI, 20033, LocalizedText.english("mg/kg"), LocalizedText.english("milligram per kilogram"));

    public static final EUInformation CODE_M29 = new EUInformation(CEFACT_NAMESPACE_URI, 5059129, LocalizedText.english("kg/kg"), LocalizedText.english("kilogram per kilogram"));

    public static final EUInformation CODE_M91 = new EUInformation(CEFACT_NAMESPACE_URI, 5060913, LocalizedText.english("lb/lb"), LocalizedText.english("pound per pound"));

    public static final EUInformation CODE_MQS = new EUInformation(CEFACT_NAMESPACE_URI, 5067091, LocalizedText.english("m³/s"), LocalizedText.english("cubic metre per second"));

    public static final EUInformation CODE_MQH = new EUInformation(CEFACT_NAMESPACE_URI, 5067080, LocalizedText.english("m³/h"), LocalizedText.english("cubic metre per hour"));

    public static final EUInformation CODE_40 = new EUInformation(CEFACT_NAMESPACE_URI, 13360, LocalizedText.english("ml/s"), LocalizedText.english("millilitre per second"));

    public static final EUInformation CODE_41 = new EUInformation(CEFACT_NAMESPACE_URI, 13361, LocalizedText.english("ml/min"), LocalizedText.english("millilitre per minute"));

    public static final EUInformation CODE_LD = new EUInformation(CEFACT_NAMESPACE_URI, 19524, LocalizedText.english("l/d"), LocalizedText.english("litre per day"));

    public static final EUInformation CODE_2J = new EUInformation(CEFACT_NAMESPACE_URI, 12874, LocalizedText.english("cm³/s"), LocalizedText.english("cubic centimetre per second"));

    public static final EUInformation CODE_4X = new EUInformation(CEFACT_NAMESPACE_URI, 13400, LocalizedText.english("kl/h"), LocalizedText.english("kilolitre per hour"));

    public static final EUInformation CODE_L2 = new EUInformation(CEFACT_NAMESPACE_URI, 19506, LocalizedText.english("l/min"), LocalizedText.english("litre per minute"));

    public static final EUInformation CODE_G47 = new EUInformation(CEFACT_NAMESPACE_URI, 4666423, LocalizedText.english("cm³/d"), LocalizedText.english("cubic centimetre per day"));

    public static final EUInformation CODE_G78 = new EUInformation(CEFACT_NAMESPACE_URI, 4667192, LocalizedText.english("cm³/(d·bar)"), LocalizedText.english("cubic centimetre per day bar"));

    public static final EUInformation CODE_G61 = new EUInformation(CEFACT_NAMESPACE_URI, 4666929, LocalizedText.english("cm³/(d·K)"), LocalizedText.english("cubic centimetre per day kelvin"));

    public static final EUInformation CODE_G48 = new EUInformation(CEFACT_NAMESPACE_URI, 4666424, LocalizedText.english("cm³/h"), LocalizedText.english("cubic centimetre per hour"));

    public static final EUInformation CODE_G79 = new EUInformation(CEFACT_NAMESPACE_URI, 4667193, LocalizedText.english("cm³/(h·bar)"), LocalizedText.english("cubic centimetre per hour bar"));

    public static final EUInformation CODE_G62 = new EUInformation(CEFACT_NAMESPACE_URI, 4666930, LocalizedText.english("cm³/(h·K)"), LocalizedText.english("cubic centimetre per hour kelvin"));

    public static final EUInformation CODE_G49 = new EUInformation(CEFACT_NAMESPACE_URI, 4666425, LocalizedText.english("cm³/min"), LocalizedText.english("cubic centimetre per minute"));

    public static final EUInformation CODE_G80 = new EUInformation(CEFACT_NAMESPACE_URI, 4667440, LocalizedText.english("cm³/(min·bar)"), LocalizedText.english("cubic centimetre per minute bar"));

    public static final EUInformation CODE_G63 = new EUInformation(CEFACT_NAMESPACE_URI, 4666931, LocalizedText.english("cm³/(min·K)"), LocalizedText.english("cubic centimetre per minute kelvin"));

    public static final EUInformation CODE_G81 = new EUInformation(CEFACT_NAMESPACE_URI, 4667441, LocalizedText.english("cm³/(s·bar)"), LocalizedText.english("cubic centimetre per second bar"));

    public static final EUInformation CODE_G64 = new EUInformation(CEFACT_NAMESPACE_URI, 4666932, LocalizedText.english("cm³/(s·K)"), LocalizedText.english("cubic centimetre per second kelvin"));

    public static final EUInformation CODE_E92 = new EUInformation(CEFACT_NAMESPACE_URI, 4536626, LocalizedText.english("dm³/h"), LocalizedText.english("cubic decimetre per hour"));

    public static final EUInformation CODE_G52 = new EUInformation(CEFACT_NAMESPACE_URI, 4666674, LocalizedText.english("m³/d"), LocalizedText.english("cubic metre per day"));

    public static final EUInformation CODE_G86 = new EUInformation(CEFACT_NAMESPACE_URI, 4667446, LocalizedText.english("m³/(d·bar)"), LocalizedText.english("cubic metre per day bar"));

    public static final EUInformation CODE_G69 = new EUInformation(CEFACT_NAMESPACE_URI, 4666937, LocalizedText.english("m³/(d·K)"), LocalizedText.english("cubic metre per day kelvin"));

    public static final EUInformation CODE_G87 = new EUInformation(CEFACT_NAMESPACE_URI, 4667447, LocalizedText.english("m³/(h·bar)"), LocalizedText.english("cubic metre per hour bar"));

    public static final EUInformation CODE_G70 = new EUInformation(CEFACT_NAMESPACE_URI, 4667184, LocalizedText.english("m³/(h·K)"), LocalizedText.english("cubic metre per hour kelvin"));

    public static final EUInformation CODE_G53 = new EUInformation(CEFACT_NAMESPACE_URI, 4666675, LocalizedText.english("m³/min"), LocalizedText.english("cubic metre per minute"));

    public static final EUInformation CODE_G88 = new EUInformation(CEFACT_NAMESPACE_URI, 4667448, LocalizedText.english("m³/(min·bar)"), LocalizedText.english("cubic metre per minute bar"));

    public static final EUInformation CODE_G71 = new EUInformation(CEFACT_NAMESPACE_URI, 4667185, LocalizedText.english("m³/(min·K)"), LocalizedText.english("cubic metre per minute kelvin"));

    public static final EUInformation CODE_G89 = new EUInformation(CEFACT_NAMESPACE_URI, 4667449, LocalizedText.english("m³/(s·bar)"), LocalizedText.english("cubic metre per second bar"));

    public static final EUInformation CODE_G72 = new EUInformation(CEFACT_NAMESPACE_URI, 4667186, LocalizedText.english("m³/(s·K)"), LocalizedText.english("cubic metre per second kelvin"));

    public static final EUInformation CODE_G82 = new EUInformation(CEFACT_NAMESPACE_URI, 4667442, LocalizedText.english("l/(d·bar)"), LocalizedText.english("litre per day bar"));

    public static final EUInformation CODE_G65 = new EUInformation(CEFACT_NAMESPACE_URI, 4666933, LocalizedText.english("l/(d·K)"), LocalizedText.english("litre per day kelvin"));

    public static final EUInformation CODE_G83 = new EUInformation(CEFACT_NAMESPACE_URI, 4667443, LocalizedText.english("l/(h·bar)"), LocalizedText.english("litre per hour bar"));

    public static final EUInformation CODE_G66 = new EUInformation(CEFACT_NAMESPACE_URI, 4666934, LocalizedText.english("l/(h·K)"), LocalizedText.english("litre per hour kelvin"));

    public static final EUInformation CODE_G84 = new EUInformation(CEFACT_NAMESPACE_URI, 4667444, LocalizedText.english("l/(min·bar)"), LocalizedText.english("litre per minute bar"));

    public static final EUInformation CODE_G67 = new EUInformation(CEFACT_NAMESPACE_URI, 4666935, LocalizedText.english("l/(min·K)"), LocalizedText.english("litre per minute kelvin"));

    public static final EUInformation CODE_G51 = new EUInformation(CEFACT_NAMESPACE_URI, 4666673, LocalizedText.english("l/s"), LocalizedText.english("litre per second"));

    public static final EUInformation CODE_G85 = new EUInformation(CEFACT_NAMESPACE_URI, 4667445, LocalizedText.english("l/(s·bar)"), LocalizedText.english("litre per second bar"));

    public static final EUInformation CODE_G68 = new EUInformation(CEFACT_NAMESPACE_URI, 4666936, LocalizedText.english("l/(s·K)"), LocalizedText.english("litre per second kelvin"));

    public static final EUInformation CODE_G54 = new EUInformation(CEFACT_NAMESPACE_URI, 4666676, LocalizedText.english("ml/d"), LocalizedText.english("millilitre per day"));

    public static final EUInformation CODE_G90 = new EUInformation(CEFACT_NAMESPACE_URI, 4667696, LocalizedText.english("ml/(d·bar)"), LocalizedText.english("millilitre per day bar"));

    public static final EUInformation CODE_G73 = new EUInformation(CEFACT_NAMESPACE_URI, 4667187, LocalizedText.english("ml/(d·K)"), LocalizedText.english("millilitre per day kelvin"));

    public static final EUInformation CODE_G55 = new EUInformation(CEFACT_NAMESPACE_URI, 4666677, LocalizedText.english("ml/h"), LocalizedText.english("millilitre per hour"));

    public static final EUInformation CODE_G91 = new EUInformation(CEFACT_NAMESPACE_URI, 4667697, LocalizedText.english("ml/(h·bar)"), LocalizedText.english("millilitre per hour bar"));

    public static final EUInformation CODE_G74 = new EUInformation(CEFACT_NAMESPACE_URI, 4667188, LocalizedText.english("ml/(h·K)"), LocalizedText.english("millilitre per hour kelvin"));

    public static final EUInformation CODE_G92 = new EUInformation(CEFACT_NAMESPACE_URI, 4667698, LocalizedText.english("ml/(min·bar)"), LocalizedText.english("millilitre per minute bar"));

    public static final EUInformation CODE_G75 = new EUInformation(CEFACT_NAMESPACE_URI, 4667189, LocalizedText.english("ml/(min·K)"), LocalizedText.english("millilitre per minute kelvin"));

    public static final EUInformation CODE_G93 = new EUInformation(CEFACT_NAMESPACE_URI, 4667699, LocalizedText.english("ml/(s·bar)"), LocalizedText.english("millilitre per second bar"));

    public static final EUInformation CODE_G76 = new EUInformation(CEFACT_NAMESPACE_URI, 4667190, LocalizedText.english("ml/(s·K)"), LocalizedText.english("millilitre per second kelvin"));

    public static final EUInformation CODE_2K = new EUInformation(CEFACT_NAMESPACE_URI, 12875, LocalizedText.english("ft³/h"), LocalizedText.english("cubic foot per hour"));

    public static final EUInformation CODE_2L = new EUInformation(CEFACT_NAMESPACE_URI, 12876, LocalizedText.english("ft³/min"), LocalizedText.english("cubic foot per minute"));

    public static final EUInformation CODE_5A = new EUInformation(CEFACT_NAMESPACE_URI, 13633, LocalizedText.english("barrel (US)/min"), LocalizedText.english("barrel (US) per minute"));

    public static final EUInformation CODE_G2 = new EUInformation(CEFACT_NAMESPACE_URI, 18226, LocalizedText.english("gal (US) /min"), LocalizedText.english("US gallon per minute"));

    public static final EUInformation CODE_G3 = new EUInformation(CEFACT_NAMESPACE_URI, 18227, LocalizedText.english("gal (UK) /min"), LocalizedText.english("Imperial gallon per minute"));

    public static final EUInformation CODE_G56 = new EUInformation(CEFACT_NAMESPACE_URI, 4666678, LocalizedText.english("in³/h"), LocalizedText.english("cubic inch per hour"));

    public static final EUInformation CODE_G57 = new EUInformation(CEFACT_NAMESPACE_URI, 4666679, LocalizedText.english("in³/min"), LocalizedText.english("cubic inch per minute"));

    public static final EUInformation CODE_G58 = new EUInformation(CEFACT_NAMESPACE_URI, 4666680, LocalizedText.english("in³/s"), LocalizedText.english("cubic inch per second"));

    public static final EUInformation CODE_G50 = new EUInformation(CEFACT_NAMESPACE_URI, 4666672, LocalizedText.english("gal/h"), LocalizedText.english("gallon (US) per hour"));

    public static final EUInformation CODE_J58 = new EUInformation(CEFACT_NAMESPACE_URI, 4863288, LocalizedText.english("bbl (UK liq.)/min"), LocalizedText.english("barrel (UK petroleum) per minute"));

    public static final EUInformation CODE_J59 = new EUInformation(CEFACT_NAMESPACE_URI, 4863289, LocalizedText.english("bbl (UK liq.)/d"), LocalizedText.english("barrel (UK petroleum) per day"));

    public static final EUInformation CODE_J60 = new EUInformation(CEFACT_NAMESPACE_URI, 4863536, LocalizedText.english("bbl (UK liq.)/h"), LocalizedText.english("barrel (UK petroleum) per hour"));

    public static final EUInformation CODE_J61 = new EUInformation(CEFACT_NAMESPACE_URI, 4863537, LocalizedText.english("bbl (UK liq.)/s"), LocalizedText.english("barrel (UK petroleum) per second"));

    public static final EUInformation CODE_J62 = new EUInformation(CEFACT_NAMESPACE_URI, 4863538, LocalizedText.english("bbl (US)/h"), LocalizedText.english("barrel (US petroleum) per hour"));

    public static final EUInformation CODE_J63 = new EUInformation(CEFACT_NAMESPACE_URI, 4863539, LocalizedText.english("bbl (US)/s"), LocalizedText.english("barrel (US petroleum) per second"));

    public static final EUInformation CODE_J64 = new EUInformation(CEFACT_NAMESPACE_URI, 4863540, LocalizedText.english("bu (UK)/d"), LocalizedText.english("bushel (UK) per day"));

    public static final EUInformation CODE_J65 = new EUInformation(CEFACT_NAMESPACE_URI, 4863541, LocalizedText.english("bu (UK)/h"), LocalizedText.english("bushel (UK) per hour"));

    public static final EUInformation CODE_J66 = new EUInformation(CEFACT_NAMESPACE_URI, 4863542, LocalizedText.english("bu (UK)/min"), LocalizedText.english("bushel (UK) per minute"));

    public static final EUInformation CODE_J67 = new EUInformation(CEFACT_NAMESPACE_URI, 4863543, LocalizedText.english("bu (UK)/s"), LocalizedText.english("bushel (UK) per second"));

    public static final EUInformation CODE_J68 = new EUInformation(CEFACT_NAMESPACE_URI, 4863544, LocalizedText.english("bu (US dry)/d"), LocalizedText.english("bushel (US dry) per day"));

    public static final EUInformation CODE_J69 = new EUInformation(CEFACT_NAMESPACE_URI, 4863545, LocalizedText.english("bu (US dry)/h"), LocalizedText.english("bushel (US dry) per hour"));

    public static final EUInformation CODE_J70 = new EUInformation(CEFACT_NAMESPACE_URI, 4863792, LocalizedText.english("bu (US dry)/min"), LocalizedText.english("bushel (US dry) per minute"));

    public static final EUInformation CODE_J71 = new EUInformation(CEFACT_NAMESPACE_URI, 4863793, LocalizedText.english("bu (US dry)/s"), LocalizedText.english("bushel (US dry) per second"));

    public static final EUInformation CODE_J90 = new EUInformation(CEFACT_NAMESPACE_URI, 4864304, LocalizedText.english("dm³/d"), LocalizedText.english("cubic decimetre per day"));

    public static final EUInformation CODE_J92 = new EUInformation(CEFACT_NAMESPACE_URI, 4864306, LocalizedText.english("dm³/min"), LocalizedText.english("cubic decimetre per minute"));

    public static final EUInformation CODE_J93 = new EUInformation(CEFACT_NAMESPACE_URI, 4864307, LocalizedText.english("dm³/s"), LocalizedText.english("cubic decimetre per second"));

    public static final EUInformation CODE_N45 = new EUInformation(CEFACT_NAMESPACE_URI, 5125173, LocalizedText.english("(m³/s)/Pa"), LocalizedText.english("cubic metre per second pascal"));

    public static final EUInformation CODE_J95 = new EUInformation(CEFACT_NAMESPACE_URI, 4864309, LocalizedText.english("fl oz (UK)/d"), LocalizedText.english("ounce (UK fluid) per day"));

    public static final EUInformation CODE_J96 = new EUInformation(CEFACT_NAMESPACE_URI, 4864310, LocalizedText.english("fl oz (UK)/h"), LocalizedText.english("ounce (UK fluid) per hour"));

    public static final EUInformation CODE_J97 = new EUInformation(CEFACT_NAMESPACE_URI, 4864311, LocalizedText.english("fl oz (UK)/min"), LocalizedText.english("ounce (UK fluid) per minute"));

    public static final EUInformation CODE_J98 = new EUInformation(CEFACT_NAMESPACE_URI, 4864312, LocalizedText.english("fl oz (UK)/s"), LocalizedText.english("ounce (UK fluid) per second"));

    public static final EUInformation CODE_J99 = new EUInformation(CEFACT_NAMESPACE_URI, 4864313, LocalizedText.english("fl oz (US)/d"), LocalizedText.english("ounce (US fluid) per day"));

    public static final EUInformation CODE_K10 = new EUInformation(CEFACT_NAMESPACE_URI, 4927792, LocalizedText.english("fl oz (US)/h"), LocalizedText.english("ounce (US fluid) per hour"));

    public static final EUInformation CODE_K11 = new EUInformation(CEFACT_NAMESPACE_URI, 4927793, LocalizedText.english("fl oz (US)/min"), LocalizedText.english("ounce (US fluid) per minute"));

    public static final EUInformation CODE_K12 = new EUInformation(CEFACT_NAMESPACE_URI, 4927794, LocalizedText.english("fl oz (US)/s"), LocalizedText.english("ounce (US fluid) per second"));

    public static final EUInformation CODE_K22 = new EUInformation(CEFACT_NAMESPACE_URI, 4928050, LocalizedText.english("ft³/d"), LocalizedText.english("cubic foot per day"));

    public static final EUInformation CODE_K26 = new EUInformation(CEFACT_NAMESPACE_URI, 4928054, LocalizedText.english("gal (UK)/d"), LocalizedText.english("gallon (UK) per day"));

    public static final EUInformation CODE_K27 = new EUInformation(CEFACT_NAMESPACE_URI, 4928055, LocalizedText.english("gal (UK)/h"), LocalizedText.english("gallon (UK) per hour"));

    public static final EUInformation CODE_K28 = new EUInformation(CEFACT_NAMESPACE_URI, 4928056, LocalizedText.english("gal (UK)/s"), LocalizedText.english("gallon (UK) per second"));

    public static final EUInformation CODE_K30 = new EUInformation(CEFACT_NAMESPACE_URI, 4928304, LocalizedText.english("gal (US liq.)/s"), LocalizedText.english("gallon (US liquid) per second"));

    public static final EUInformation CODE_K32 = new EUInformation(CEFACT_NAMESPACE_URI, 4928306, LocalizedText.english("gi (UK)/d"), LocalizedText.english("gill (UK) per day"));

    public static final EUInformation CODE_K33 = new EUInformation(CEFACT_NAMESPACE_URI, 4928307, LocalizedText.english("gi (UK)/h"), LocalizedText.english("gill (UK) per hour"));

    public static final EUInformation CODE_K34 = new EUInformation(CEFACT_NAMESPACE_URI, 4928308, LocalizedText.english("gi (UK)/min"), LocalizedText.english("gill (UK) per minute"));

    public static final EUInformation CODE_K35 = new EUInformation(CEFACT_NAMESPACE_URI, 4928309, LocalizedText.english("gi (UK)/s"), LocalizedText.english("gill (UK) per second"));

    public static final EUInformation CODE_K36 = new EUInformation(CEFACT_NAMESPACE_URI, 4928310, LocalizedText.english("gi (US)/d"), LocalizedText.english("gill (US) per day"));

    public static final EUInformation CODE_K37 = new EUInformation(CEFACT_NAMESPACE_URI, 4928311, LocalizedText.english("gi (US)/h"), LocalizedText.english("gill (US) per hour"));

    public static final EUInformation CODE_K38 = new EUInformation(CEFACT_NAMESPACE_URI, 4928312, LocalizedText.english("gi (US)/min"), LocalizedText.english("gill (US) per minute"));

    public static final EUInformation CODE_K39 = new EUInformation(CEFACT_NAMESPACE_URI, 4928313, LocalizedText.english("gi (US)/s"), LocalizedText.english("gill (US) per second"));

    public static final EUInformation CODE_K94 = new EUInformation(CEFACT_NAMESPACE_URI, 4929844, LocalizedText.english("qt (UK liq.)/d"), LocalizedText.english("quart (UK liquid) per day"));

    public static final EUInformation CODE_K95 = new EUInformation(CEFACT_NAMESPACE_URI, 4929845, LocalizedText.english("qt (UK liq.)/h"), LocalizedText.english("quart (UK liquid) per hour"));

    public static final EUInformation CODE_K96 = new EUInformation(CEFACT_NAMESPACE_URI, 4929846, LocalizedText.english("qt (UK liq.)/min"), LocalizedText.english("quart (UK liquid) per minute"));

    public static final EUInformation CODE_K97 = new EUInformation(CEFACT_NAMESPACE_URI, 4929847, LocalizedText.english("qt (UK liq.)/s"), LocalizedText.english("quart (UK liquid) per second"));

    public static final EUInformation CODE_K98 = new EUInformation(CEFACT_NAMESPACE_URI, 4929848, LocalizedText.english("qt (US liq.)/d"), LocalizedText.english("quart (US liquid) per day"));

    public static final EUInformation CODE_K99 = new EUInformation(CEFACT_NAMESPACE_URI, 4929849, LocalizedText.english("qt (US liq.)/h"), LocalizedText.english("quart (US liquid) per hour"));

    public static final EUInformation CODE_L10 = new EUInformation(CEFACT_NAMESPACE_URI, 4993328, LocalizedText.english("qt (US liq.)/min"), LocalizedText.english("quart (US liquid) per minute"));

    public static final EUInformation CODE_L11 = new EUInformation(CEFACT_NAMESPACE_URI, 4993329, LocalizedText.english("qt (US liq.)/s"), LocalizedText.english("quart (US liquid) per second"));

    public static final EUInformation CODE_L44 = new EUInformation(CEFACT_NAMESPACE_URI, 4994100, LocalizedText.english("pk (UK)/d"), LocalizedText.english("peck (UK) per day"));

    public static final EUInformation CODE_L45 = new EUInformation(CEFACT_NAMESPACE_URI, 4994101, LocalizedText.english("pk (UK)/h"), LocalizedText.english("peck (UK) per hour"));

    public static final EUInformation CODE_L46 = new EUInformation(CEFACT_NAMESPACE_URI, 4994102, LocalizedText.english("pk (UK)/min"), LocalizedText.english("peck (UK) per minute"));

    public static final EUInformation CODE_L47 = new EUInformation(CEFACT_NAMESPACE_URI, 4994103, LocalizedText.english("pk (UK)/s"), LocalizedText.english("peck (UK) per second"));

    public static final EUInformation CODE_L48 = new EUInformation(CEFACT_NAMESPACE_URI, 4994104, LocalizedText.english("pk (US dry)/d"), LocalizedText.english("peck (US dry) per day"));

    public static final EUInformation CODE_L49 = new EUInformation(CEFACT_NAMESPACE_URI, 4994105, LocalizedText.english("pk (US dry)/h"), LocalizedText.english("peck (US dry) per hour"));

    public static final EUInformation CODE_L50 = new EUInformation(CEFACT_NAMESPACE_URI, 4994352, LocalizedText.english("pk (US dry)/min"), LocalizedText.english("peck (US dry) per minute"));

    public static final EUInformation CODE_L51 = new EUInformation(CEFACT_NAMESPACE_URI, 4994353, LocalizedText.english("pk (US dry)/s"), LocalizedText.english("peck (US dry) per second"));

    public static final EUInformation CODE_L53 = new EUInformation(CEFACT_NAMESPACE_URI, 4994355, LocalizedText.english("pt (UK)/d"), LocalizedText.english("pint (UK) per day"));

    public static final EUInformation CODE_L54 = new EUInformation(CEFACT_NAMESPACE_URI, 4994356, LocalizedText.english("pt (UK)/h"), LocalizedText.english("pint (UK) per hour"));

    public static final EUInformation CODE_L55 = new EUInformation(CEFACT_NAMESPACE_URI, 4994357, LocalizedText.english("pt (UK)/min"), LocalizedText.english("pint (UK) per minute"));

    public static final EUInformation CODE_L56 = new EUInformation(CEFACT_NAMESPACE_URI, 4994358, LocalizedText.english("pt (UK)/s"), LocalizedText.english("pint (UK) per second"));

    public static final EUInformation CODE_L57 = new EUInformation(CEFACT_NAMESPACE_URI, 4994359, LocalizedText.english("pt (US liq.)/d"), LocalizedText.english("pint (US liquid) per day"));

    public static final EUInformation CODE_L58 = new EUInformation(CEFACT_NAMESPACE_URI, 4994360, LocalizedText.english("pt (US liq.)/h"), LocalizedText.english("pint (US liquid) per hour"));

    public static final EUInformation CODE_L59 = new EUInformation(CEFACT_NAMESPACE_URI, 4994361, LocalizedText.english("pt (US liq.)/min"), LocalizedText.english("pint (US liquid) per minute"));

    public static final EUInformation CODE_L60 = new EUInformation(CEFACT_NAMESPACE_URI, 4994608, LocalizedText.english("pt (US liq.)/s"), LocalizedText.english("pint (US liquid) per second"));

    public static final EUInformation CODE_M12 = new EUInformation(CEFACT_NAMESPACE_URI, 5058866, LocalizedText.english("yd³/d"), LocalizedText.english("cubic yard per day"));

    public static final EUInformation CODE_M13 = new EUInformation(CEFACT_NAMESPACE_URI, 5058867, LocalizedText.english("yd³/h"), LocalizedText.english("cubic yard per hour"));

    public static final EUInformation CODE_M15 = new EUInformation(CEFACT_NAMESPACE_URI, 5058869, LocalizedText.english("yd³/min"), LocalizedText.english("cubic yard per minute"));

    public static final EUInformation CODE_M16 = new EUInformation(CEFACT_NAMESPACE_URI, 5058870, LocalizedText.english("yd³/s"), LocalizedText.english("cubic yard per second"));

    public static final EUInformation CODE_H60 = new EUInformation(CEFACT_NAMESPACE_URI, 4732464, LocalizedText.english("m³/m³"), LocalizedText.english("cubic metre per cubic metre"));

    public static final EUInformation CODE_F92 = new EUInformation(CEFACT_NAMESPACE_URI, 4602162, LocalizedText.english("bar·m³/s"), LocalizedText.english("bar cubic metre per second"));

    public static final EUInformation CODE_F91 = new EUInformation(CEFACT_NAMESPACE_URI, 4602161, LocalizedText.english("bar·l/s"), LocalizedText.english("bar litre per second"));

    public static final EUInformation CODE_K87 = new EUInformation(CEFACT_NAMESPACE_URI, 4929591, LocalizedText.english("psi·in³/s"), LocalizedText.english("psi cubic inch per second"));

    public static final EUInformation CODE_K88 = new EUInformation(CEFACT_NAMESPACE_URI, 4929592, LocalizedText.english("psi·l/s"), LocalizedText.english("psi litre per second"));

    public static final EUInformation CODE_K89 = new EUInformation(CEFACT_NAMESPACE_URI, 4929593, LocalizedText.english("psi·m³/s"), LocalizedText.english("psi cubic metre per second"));

    public static final EUInformation CODE_K90 = new EUInformation(CEFACT_NAMESPACE_URI, 4929840, LocalizedText.english("psi·yd³/s"), LocalizedText.english("psi cubic yard per second"));

    public static final EUInformation CODE_Q29 = new EUInformation(CEFACT_NAMESPACE_URI, 5321273, LocalizedText.english("µg/hg"), LocalizedText.english("microgram per hectogram"));

    public static final EUInformation CODE_KEL = new EUInformation(CEFACT_NAMESPACE_URI, 4932940, LocalizedText.english("K"), LocalizedText.english("kelvin"));

    public static final EUInformation CODE_CEL = new EUInformation(CEFACT_NAMESPACE_URI, 4408652, LocalizedText.english("°C"), LocalizedText.english("degree Celsius"));

    public static final EUInformation CODE_H12 = new EUInformation(CEFACT_NAMESPACE_URI, 4731186, LocalizedText.english("°C/h"), LocalizedText.english("degree Celsius per hour"));

    public static final EUInformation CODE_F60 = new EUInformation(CEFACT_NAMESPACE_URI, 4601392, LocalizedText.english("°C/bar"), LocalizedText.english("degree Celsius per bar"));

    public static final EUInformation CODE_E98 = new EUInformation(CEFACT_NAMESPACE_URI, 4536632, LocalizedText.english("°C/K"), LocalizedText.english("degree Celsius per kelvin"));

    public static final EUInformation CODE_H13 = new EUInformation(CEFACT_NAMESPACE_URI, 4731187, LocalizedText.english("°C/min"), LocalizedText.english("degree Celsius per minute"));

    public static final EUInformation CODE_H14 = new EUInformation(CEFACT_NAMESPACE_URI, 4731188, LocalizedText.english("°C/s"), LocalizedText.english("degree Celsius per second"));

    public static final EUInformation CODE_F61 = new EUInformation(CEFACT_NAMESPACE_URI, 4601393, LocalizedText.english("K/bar"), LocalizedText.english("kelvin per bar"));

    public static final EUInformation CODE_F10 = new EUInformation(CEFACT_NAMESPACE_URI, 4600112, LocalizedText.english("K/h"), LocalizedText.english("kelvin per hour"));

    public static final EUInformation CODE_F02 = new EUInformation(CEFACT_NAMESPACE_URI, 4599858, LocalizedText.english("K/K"), LocalizedText.english("kelvin per kelvin"));

    public static final EUInformation CODE_F11 = new EUInformation(CEFACT_NAMESPACE_URI, 4600113, LocalizedText.english("K/min"), LocalizedText.english("kelvin per minute"));

    public static final EUInformation CODE_F12 = new EUInformation(CEFACT_NAMESPACE_URI, 4600114, LocalizedText.english("K/s"), LocalizedText.english("kelvin per second"));

    public static final EUInformation CODE_N79 = new EUInformation(CEFACT_NAMESPACE_URI, 5125945, LocalizedText.english("K/Pa"), LocalizedText.english("kelvin per pascal"));

    public static final EUInformation CODE_J20 = new EUInformation(CEFACT_NAMESPACE_URI, 4862512, LocalizedText.english("°F/K"), LocalizedText.english("degree Fahrenheit per kelvin"));

    public static final EUInformation CODE_J21 = new EUInformation(CEFACT_NAMESPACE_URI, 4862513, LocalizedText.english("°F/bar"), LocalizedText.english("degree Fahrenheit per bar"));

    public static final EUInformation CODE_J26 = new EUInformation(CEFACT_NAMESPACE_URI, 4862518, LocalizedText.english("1/°F"), LocalizedText.english("reciprocal degree Fahrenheit"));

    public static final EUInformation CODE_A48 = new EUInformation(CEFACT_NAMESPACE_URI, 4273208, LocalizedText.english("°R"), LocalizedText.english("degree Rankine"));

    public static final EUInformation CODE_FAH = new EUInformation(CEFACT_NAMESPACE_URI, 4604232, LocalizedText.english("°F"), LocalizedText.english("degree Fahrenheit"));

    public static final EUInformation CODE_J23 = new EUInformation(CEFACT_NAMESPACE_URI, 4862515, LocalizedText.english("°F/h"), LocalizedText.english("degree Fahrenheit per hour"));

    public static final EUInformation CODE_J24 = new EUInformation(CEFACT_NAMESPACE_URI, 4862516, LocalizedText.english("°F/min"), LocalizedText.english("degree Fahrenheit per minute"));

    public static final EUInformation CODE_J25 = new EUInformation(CEFACT_NAMESPACE_URI, 4862517, LocalizedText.english("°F/s"), LocalizedText.english("degree Fahrenheit per second"));

    public static final EUInformation CODE_J28 = new EUInformation(CEFACT_NAMESPACE_URI, 4862520, LocalizedText.english("°R/h"), LocalizedText.english("degree Rankine per hour"));

    public static final EUInformation CODE_J29 = new EUInformation(CEFACT_NAMESPACE_URI, 4862521, LocalizedText.english("°R/min"), LocalizedText.english("degree Rankine per minute"));

    public static final EUInformation CODE_J30 = new EUInformation(CEFACT_NAMESPACE_URI, 4862768, LocalizedText.english("°R/s"), LocalizedText.english("degree Rankine per second"));

    public static final EUInformation CODE_C91 = new EUInformation(CEFACT_NAMESPACE_URI, 4405553, LocalizedText.english("K⁻¹"), LocalizedText.english("reciprocal kelvin or kelvin to the power minus one"));

    public static final EUInformation CODE_M20 = new EUInformation(CEFACT_NAMESPACE_URI, 5059120, LocalizedText.english("1/MK"), LocalizedText.english("reciprocal megakelvin or megakelvin to the power minus one"));

    public static final EUInformation CODE_C64 = new EUInformation(CEFACT_NAMESPACE_URI, 4404788, LocalizedText.english("Pa/K"), LocalizedText.english("pascal per kelvin"));

    public static final EUInformation CODE_F81 = new EUInformation(CEFACT_NAMESPACE_URI, 4601905, LocalizedText.english("bar/K"), LocalizedText.english("bar per kelvin"));

    public static final EUInformation CODE_J55 = new EUInformation(CEFACT_NAMESPACE_URI, 4863285, LocalizedText.english("W·s"), LocalizedText.english("watt second"));

    public static final EUInformation CODE_BTU = new EUInformation(CEFACT_NAMESPACE_URI, 4346965, LocalizedText.english("BtuIT"), LocalizedText.english("British thermal unit (international table)"));

    public static final EUInformation CODE_A1 = new EUInformation(CEFACT_NAMESPACE_URI, 16689, LocalizedText.english("cal₁₅"), LocalizedText.english("15 °C calorie"));

    public static final EUInformation CODE_D70 = new EUInformation(CEFACT_NAMESPACE_URI, 4470576, LocalizedText.english("calIT"), LocalizedText.english("calorie (international table)"));

    public static final EUInformation CODE_J39 = new EUInformation(CEFACT_NAMESPACE_URI, 4862777, LocalizedText.english("Btu"), LocalizedText.english("British thermal unit (mean)"));

    public static final EUInformation CODE_J75 = new EUInformation(CEFACT_NAMESPACE_URI, 4863797, LocalizedText.english("cal"), LocalizedText.english("calorie (mean)"));

    public static final EUInformation CODE_K51 = new EUInformation(CEFACT_NAMESPACE_URI, 4928817, LocalizedText.english("kcal"), LocalizedText.english("kilocalorie (mean)"));

    public static final EUInformation CODE_E14 = new EUInformation(CEFACT_NAMESPACE_URI, 4534580, LocalizedText.english("kcalIT"), LocalizedText.english("kilocalorie (international table)"));

    public static final EUInformation CODE_K53 = new EUInformation(CEFACT_NAMESPACE_URI, 4928819, LocalizedText.english("kcalth"), LocalizedText.english("kilocalorie (thermochemical)"));

    public static final EUInformation CODE_N66 = new EUInformation(CEFACT_NAMESPACE_URI, 5125686, LocalizedText.english("Btu (39 ºF)"), LocalizedText.english("British thermal unit (39 ºF)"));

    public static final EUInformation CODE_N67 = new EUInformation(CEFACT_NAMESPACE_URI, 5125687, LocalizedText.english("Btu (59 ºF)"), LocalizedText.english("British thermal unit (59 ºF)"));

    public static final EUInformation CODE_N68 = new EUInformation(CEFACT_NAMESPACE_URI, 5125688, LocalizedText.english("Btu (60 ºF)"), LocalizedText.english("British thermal unit (60 ºF)"));

    public static final EUInformation CODE_N69 = new EUInformation(CEFACT_NAMESPACE_URI, 5125689, LocalizedText.english("cal₂₀"), LocalizedText.english("calorie (20 ºC)"));

    public static final EUInformation CODE_N70 = new EUInformation(CEFACT_NAMESPACE_URI, 5125936, LocalizedText.english("quad"), LocalizedText.english("quad (1015 BtuIT)"));

    public static final EUInformation CODE_N71 = new EUInformation(CEFACT_NAMESPACE_URI, 5125937, LocalizedText.english("thm (EC)"), LocalizedText.english("therm (EC)"));

    public static final EUInformation CODE_N72 = new EUInformation(CEFACT_NAMESPACE_URI, 5125938, LocalizedText.english("thm (US)"), LocalizedText.english("therm (U.S.)"));

    public static final EUInformation CODE_D35 = new EUInformation(CEFACT_NAMESPACE_URI, 4469557, LocalizedText.english("calth"), LocalizedText.english("calorie (thermochemical)"));

    public static final EUInformation CODE_2I = new EUInformation(CEFACT_NAMESPACE_URI, 12873, LocalizedText.english("BtuIT/h"), LocalizedText.english("British thermal unit (international table) per hour"));

    public static final EUInformation CODE_J44 = new EUInformation(CEFACT_NAMESPACE_URI, 4863028, LocalizedText.english("BtuIT/min"), LocalizedText.english("British thermal unit (international table) per minute"));

    public static final EUInformation CODE_J45 = new EUInformation(CEFACT_NAMESPACE_URI, 4863029, LocalizedText.english("BtuIT/s"), LocalizedText.english("British thermal unit (international table) per second"));

    public static final EUInformation CODE_J47 = new EUInformation(CEFACT_NAMESPACE_URI, 4863031, LocalizedText.english("Btuth/h"), LocalizedText.english("British thermal unit (thermochemical) per hour"));

    public static final EUInformation CODE_J51 = new EUInformation(CEFACT_NAMESPACE_URI, 4863281, LocalizedText.english("Btuth/min"), LocalizedText.english("British thermal unit (thermochemical) per minute"));

    public static final EUInformation CODE_J52 = new EUInformation(CEFACT_NAMESPACE_URI, 4863282, LocalizedText.english("Btuth/s"), LocalizedText.english("British thermal unit (thermochemical) per second"));

    public static final EUInformation CODE_J81 = new EUInformation(CEFACT_NAMESPACE_URI, 4864049, LocalizedText.english("calth/min"), LocalizedText.english("calorie (thermochemical) per minute"));

    public static final EUInformation CODE_J82 = new EUInformation(CEFACT_NAMESPACE_URI, 4864050, LocalizedText.english("calth/s"), LocalizedText.english("calorie (thermochemical) per second"));

    public static final EUInformation CODE_E15 = new EUInformation(CEFACT_NAMESPACE_URI, 4534581, LocalizedText.english("kcalth/h"), LocalizedText.english("kilocalorie (thermochemical) per hour"));

    public static final EUInformation CODE_K54 = new EUInformation(CEFACT_NAMESPACE_URI, 4928820, LocalizedText.english("kcalth/min"), LocalizedText.english("kilocalorie (thermochemical) per minute"));

    public static final EUInformation CODE_K55 = new EUInformation(CEFACT_NAMESPACE_URI, 4928821, LocalizedText.english("kcalth/s"), LocalizedText.english("kilocalorie (thermochemical) per second"));

    public static final EUInformation CODE_D54 = new EUInformation(CEFACT_NAMESPACE_URI, 4470068, LocalizedText.english("W/m²"), LocalizedText.english("watt per square metre"));

    public static final EUInformation CODE_N48 = new EUInformation(CEFACT_NAMESPACE_URI, 5125176, LocalizedText.english("W/cm²"), LocalizedText.english("watt per square centimetre"));

    public static final EUInformation CODE_N49 = new EUInformation(CEFACT_NAMESPACE_URI, 5125177, LocalizedText.english("W/in²"), LocalizedText.english("watt per square inch"));

    public static final EUInformation CODE_N50 = new EUInformation(CEFACT_NAMESPACE_URI, 5125424, LocalizedText.english("BtuIT/(ft²·h)"), LocalizedText.english("British thermal unit (international table) per square foot hour"));

    public static final EUInformation CODE_N51 = new EUInformation(CEFACT_NAMESPACE_URI, 5125425, LocalizedText.english("Btuth/(ft²·h)"), LocalizedText.english("British thermal unit (thermochemical) per square foot hour"));

    public static final EUInformation CODE_N52 = new EUInformation(CEFACT_NAMESPACE_URI, 5125426, LocalizedText.english("Btuth/(ft²·min)"), LocalizedText.english("British thermal unit (thermochemical) per square foot minute"));

    public static final EUInformation CODE_N53 = new EUInformation(CEFACT_NAMESPACE_URI, 5125427, LocalizedText.english("BtuIT/(ft²·s)"), LocalizedText.english("British thermal unit (international table) per square foot second"));

    public static final EUInformation CODE_N54 = new EUInformation(CEFACT_NAMESPACE_URI, 5125428, LocalizedText.english("Btuth/(ft²·s)"), LocalizedText.english("British thermal unit (thermochemical) per square foot second"));

    public static final EUInformation CODE_N55 = new EUInformation(CEFACT_NAMESPACE_URI, 5125429, LocalizedText.english("BtuIT/(in²·s)"), LocalizedText.english("British thermal unit (international table) per square inch second"));

    public static final EUInformation CODE_N56 = new EUInformation(CEFACT_NAMESPACE_URI, 5125430, LocalizedText.english("calth/(cm²·min)"), LocalizedText.english("calorie (thermochemical) per square centimetre minute"));

    public static final EUInformation CODE_N57 = new EUInformation(CEFACT_NAMESPACE_URI, 5125431, LocalizedText.english("calth/(cm²·s)"), LocalizedText.english("calorie (thermochemical) per square centimetre second"));

    public static final EUInformation CODE_D53 = new EUInformation(CEFACT_NAMESPACE_URI, 4470067, LocalizedText.english("W/(m·K)"), LocalizedText.english("watt per metre kelvin"));

    public static final EUInformation CODE_N80 = new EUInformation(CEFACT_NAMESPACE_URI, 5126192, LocalizedText.english("W/(m·°C)"), LocalizedText.english("watt per metre degree Celsius"));

    public static final EUInformation CODE_N81 = new EUInformation(CEFACT_NAMESPACE_URI, 5126193, LocalizedText.english("kW/(m·K)"), LocalizedText.english("kilowatt per metre kelvin"));

    public static final EUInformation CODE_N82 = new EUInformation(CEFACT_NAMESPACE_URI, 5126194, LocalizedText.english("kW/(m·°C)"), LocalizedText.english("kilowatt per metre degree Celsius"));

    public static final EUInformation CODE_A22 = new EUInformation(CEFACT_NAMESPACE_URI, 4272690, LocalizedText.english("BtuIT/(s·ft·°R)"), LocalizedText.english("British thermal unit (international table) per second foot degree Rankine"));

    public static final EUInformation CODE_D71 = new EUInformation(CEFACT_NAMESPACE_URI, 4470577, LocalizedText.english("calIT/(s·cm·K)"), LocalizedText.english("calorie (international table) per second centimetre kelvin"));

    public static final EUInformation CODE_D38 = new EUInformation(CEFACT_NAMESPACE_URI, 4469560, LocalizedText.english("calth/(s·cm·K)"), LocalizedText.english("calorie (thermochemical) per second centimetre kelvin"));

    public static final EUInformation CODE_J40 = new EUInformation(CEFACT_NAMESPACE_URI, 4863024, LocalizedText.english("BtuIT·ft/(h·ft²·°F)"), LocalizedText.english("British thermal unit (international table) foot per hour square foot degree Fahrenheit"));

    public static final EUInformation CODE_J41 = new EUInformation(CEFACT_NAMESPACE_URI, 4863025, LocalizedText.english("BtuIT·in/(h·ft²·°F)"), LocalizedText.english("British thermal unit (international table) inch per hour square foot degree Fahrenheit"));

    public static final EUInformation CODE_J42 = new EUInformation(CEFACT_NAMESPACE_URI, 4863026, LocalizedText.english("BtuIT·in/(s·ft²·°F)"), LocalizedText.english("British thermal unit (international table) inch per second square foot degree Fahrenheit"));

    public static final EUInformation CODE_J46 = new EUInformation(CEFACT_NAMESPACE_URI, 4863030, LocalizedText.english("Btuth·ft/(h·ft²·°F)"), LocalizedText.english("British thermal unit (thermochemical) foot per hour square foot degree Fahrenheit"));

    public static final EUInformation CODE_J48 = new EUInformation(CEFACT_NAMESPACE_URI, 4863032, LocalizedText.english("Btuth·in/(h·ft²·°F)"), LocalizedText.english("British thermal unit (thermochemical) inch per hour square foot degree Fahrenheit"));

    public static final EUInformation CODE_J49 = new EUInformation(CEFACT_NAMESPACE_URI, 4863033, LocalizedText.english("Btuth·in/(s·ft²·°F)"), LocalizedText.english("British thermal unit (thermochemical) inch per second square foot degree Fahrenheit"));

    public static final EUInformation CODE_J78 = new EUInformation(CEFACT_NAMESPACE_URI, 4863800, LocalizedText.english("calth/(cm·s·°C)"), LocalizedText.english("calorie (thermochemical) per centimetre second degree Celsius"));

    public static final EUInformation CODE_K52 = new EUInformation(CEFACT_NAMESPACE_URI, 4928818, LocalizedText.english("kcal/(m·h·°C)"), LocalizedText.english("kilocalorie (international table) per hour metre degree Celsius"));

    public static final EUInformation CODE_D55 = new EUInformation(CEFACT_NAMESPACE_URI, 4470069, LocalizedText.english("W/(m²·K)"), LocalizedText.english("watt per square metre kelvin"));

    public static final EUInformation CODE_N78 = new EUInformation(CEFACT_NAMESPACE_URI, 5125944, LocalizedText.english("kW/(m²·K)"), LocalizedText.english("kilowatt per square metre kelvin"));

    public static final EUInformation CODE_D72 = new EUInformation(CEFACT_NAMESPACE_URI, 4470578, LocalizedText.english("calIT/(s·cm²·K)"), LocalizedText.english("calorie (international table) per second square centimetre kelvin"));

    public static final EUInformation CODE_D39 = new EUInformation(CEFACT_NAMESPACE_URI, 4469561, LocalizedText.english("calth/(s·cm²·K)"), LocalizedText.english("calorie (thermochemical) per second square centimetre kelvin"));

    public static final EUInformation CODE_A20 = new EUInformation(CEFACT_NAMESPACE_URI, 4272688, LocalizedText.english("BtuIT/(s·ft²·°R)"), LocalizedText.english("British thermal unit (international table) per second square foot degree Rankine"));

    public static final EUInformation CODE_A23 = new EUInformation(CEFACT_NAMESPACE_URI, 4272691, LocalizedText.english("BtuIT/(h·ft²·°R)"), LocalizedText.english("British thermal unit (international table) per hour square foot degree Rankine"));

    public static final EUInformation CODE_N74 = new EUInformation(CEFACT_NAMESPACE_URI, 5125940, LocalizedText.english("BtuIT/(h·ft²·ºF)"), LocalizedText.english("British thermal unit (international table) per hour square foot degree Fahrenheit"));

    public static final EUInformation CODE_N75 = new EUInformation(CEFACT_NAMESPACE_URI, 5125941, LocalizedText.english("Btuth/(h·ft²·ºF)"), LocalizedText.english("British thermal unit (thermochemical) per hour square foot degree Fahrenheit"));

    public static final EUInformation CODE_N76 = new EUInformation(CEFACT_NAMESPACE_URI, 5125942, LocalizedText.english("BtuIT/(s·ft²·ºF)"), LocalizedText.english("British thermal unit (international table) per second square foot degree Fahrenheit"));

    public static final EUInformation CODE_N77 = new EUInformation(CEFACT_NAMESPACE_URI, 5125943, LocalizedText.english("Btuth/(s·ft²·ºF)"), LocalizedText.english("British thermal unit (thermochemical) per second square foot degree Fahrenheit"));

    public static final EUInformation CODE_D19 = new EUInformation(CEFACT_NAMESPACE_URI, 4469049, LocalizedText.english("m²·K/W"), LocalizedText.english("square metre kelvin per watt"));

    public static final EUInformation CODE_J19 = new EUInformation(CEFACT_NAMESPACE_URI, 4862265, LocalizedText.english("°F·h·ft²/Btuth"), LocalizedText.english("degree Fahrenheit hour square foot per British thermal unit (thermochemical)"));

    public static final EUInformation CODE_J22 = new EUInformation(CEFACT_NAMESPACE_URI, 4862514, LocalizedText.english("°F·h·ft²/BtuIT"), LocalizedText.english("degree Fahrenheit hour square foot per British thermal unit (international table)"));

    public static final EUInformation CODE_J83 = new EUInformation(CEFACT_NAMESPACE_URI, 4864051, LocalizedText.english("clo"), LocalizedText.english("clo"));

    public static final EUInformation CODE_L14 = new EUInformation(CEFACT_NAMESPACE_URI, 4993332, LocalizedText.english("m²·h·°C/kcal"), LocalizedText.english("square metre hour degree Celsius per kilocalorie (international table)"));

    public static final EUInformation CODE_B21 = new EUInformation(CEFACT_NAMESPACE_URI, 4338225, LocalizedText.english("K/W"), LocalizedText.english("kelvin per watt"));

    public static final EUInformation CODE_H35 = new EUInformation(CEFACT_NAMESPACE_URI, 4731701, LocalizedText.english("K·m/W"), LocalizedText.english("kelvin metre per watt"));

    public static final EUInformation CODE_N84 = new EUInformation(CEFACT_NAMESPACE_URI, 5126196, LocalizedText.english("ºF/(BtuIT/h)"), LocalizedText.english("degree Fahrenheit hour per British thermal unit (international table)"));

    public static final EUInformation CODE_N85 = new EUInformation(CEFACT_NAMESPACE_URI, 5126197, LocalizedText.english("ºF/(Btuth/h)"), LocalizedText.english("degree Fahrenheit hour per British thermal unit (thermochemical)"));

    public static final EUInformation CODE_N86 = new EUInformation(CEFACT_NAMESPACE_URI, 5126198, LocalizedText.english("ºF/(BtuIT/s)"), LocalizedText.english("degree Fahrenheit second per British thermal unit (international table)"));

    public static final EUInformation CODE_N87 = new EUInformation(CEFACT_NAMESPACE_URI, 5126199, LocalizedText.english("ºF/(Btuth/s)"), LocalizedText.english("degree Fahrenheit second per British thermal unit (thermochemical)"));

    public static final EUInformation CODE_N88 = new EUInformation(CEFACT_NAMESPACE_URI, 5126200, LocalizedText.english("ºF·h·ft²/(BtuIT·in)"), LocalizedText.english("degree Fahrenheit hour square foot per British thermal unit (international table) inch"));

    public static final EUInformation CODE_N89 = new EUInformation(CEFACT_NAMESPACE_URI, 5126201, LocalizedText.english("ºF·h·ft²/(Btuth·in)"), LocalizedText.english("degree Fahrenheit hour square foot per British thermal unit (thermochemical) inch"));

    public static final EUInformation CODE_D52 = new EUInformation(CEFACT_NAMESPACE_URI, 4470066, LocalizedText.english("W/K"), LocalizedText.english("watt per kelvin"));

    public static final EUInformation CODE_E97 = new EUInformation(CEFACT_NAMESPACE_URI, 4536631, LocalizedText.english("mm/(°C·m)"), LocalizedText.english("millimetre per degree Celcius metre"));

    public static final EUInformation CODE_F53 = new EUInformation(CEFACT_NAMESPACE_URI, 4601139, LocalizedText.english("mm/K"), LocalizedText.english("millimetre per kelvin"));

    public static final EUInformation CODE_N83 = new EUInformation(CEFACT_NAMESPACE_URI, 5126195, LocalizedText.english("m/(°C·m)"), LocalizedText.english("metre per degree Celcius metre"));

    public static final EUInformation CODE_JE = new EUInformation(CEFACT_NAMESPACE_URI, 19013, LocalizedText.english("J/K"), LocalizedText.english("joule per kelvin"));

    public static final EUInformation CODE_B41 = new EUInformation(CEFACT_NAMESPACE_URI, 4338737, LocalizedText.english("kJ/K"), LocalizedText.english("kilojoule per kelvin"));

    public static final EUInformation CODE_J43 = new EUInformation(CEFACT_NAMESPACE_URI, 4863027, LocalizedText.english("BtuIT/(lb·°F)"), LocalizedText.english("British thermal unit (international table) per pound degree Fahrenheit"));

    public static final EUInformation CODE_J50 = new EUInformation(CEFACT_NAMESPACE_URI, 4863280, LocalizedText.english("Btuth/(lb·°F)"), LocalizedText.english("British thermal unit (thermochemical) per pound degree Fahrenheit"));

    public static final EUInformation CODE_J76 = new EUInformation(CEFACT_NAMESPACE_URI, 4863798, LocalizedText.english("calIT/(g·°C)"), LocalizedText.english("calorie (international table) per gram degree Celsius"));

    public static final EUInformation CODE_J79 = new EUInformation(CEFACT_NAMESPACE_URI, 4863801, LocalizedText.english("calth/(g·°C)"), LocalizedText.english("calorie (thermochemical) per gram degree Celsius"));

    public static final EUInformation CODE_N60 = new EUInformation(CEFACT_NAMESPACE_URI, 5125680, LocalizedText.english("BtuIT/ºF"), LocalizedText.english("British thermal unit (international table) per degree Fahrenheit"));

    public static final EUInformation CODE_N61 = new EUInformation(CEFACT_NAMESPACE_URI, 5125681, LocalizedText.english("Btuth/ºF"), LocalizedText.english("British thermal unit (thermochemical) per degree Fahrenheit"));

    public static final EUInformation CODE_N62 = new EUInformation(CEFACT_NAMESPACE_URI, 5125682, LocalizedText.english("BtuIT/ºR"), LocalizedText.english("British thermal unit (international table) per degree Rankine"));

    public static final EUInformation CODE_N63 = new EUInformation(CEFACT_NAMESPACE_URI, 5125683, LocalizedText.english("Btuth/ºR"), LocalizedText.english("British thermal unit (thermochemical) per degree Rankine"));

    public static final EUInformation CODE_N64 = new EUInformation(CEFACT_NAMESPACE_URI, 5125684, LocalizedText.english("(Btuth/°R)/lb"), LocalizedText.english("British thermal unit (thermochemical) per pound degree Rankine"));

    public static final EUInformation CODE_N65 = new EUInformation(CEFACT_NAMESPACE_URI, 5125685, LocalizedText.english("(kcalIT/K)/g"), LocalizedText.english("kilocalorie (international table) per gram kelvin"));

    public static final EUInformation CODE_B11 = new EUInformation(CEFACT_NAMESPACE_URI, 4337969, LocalizedText.english("J/(kg·K)"), LocalizedText.english("joule per kilogram kelvin"));

    public static final EUInformation CODE_B43 = new EUInformation(CEFACT_NAMESPACE_URI, 4338739, LocalizedText.english("kJ/(kg·K)"), LocalizedText.english("kilojoule per kilogram kelvin"));

    public static final EUInformation CODE_A21 = new EUInformation(CEFACT_NAMESPACE_URI, 4272689, LocalizedText.english("Btu/IT(lb·°R)"), LocalizedText.english("British thermal unit (international table) per pound degree Rankine"));

    public static final EUInformation CODE_D76 = new EUInformation(CEFACT_NAMESPACE_URI, 4470582, LocalizedText.english("calIT/(g·K)"), LocalizedText.english("calorie (international table) per gram kelvin"));

    public static final EUInformation CODE_D37 = new EUInformation(CEFACT_NAMESPACE_URI, 4469559, LocalizedText.english("calth/(g·K)"), LocalizedText.english("calorie (thermochemical) per gram kelvin"));

    public static final EUInformation CODE_J2 = new EUInformation(CEFACT_NAMESPACE_URI, 18994, LocalizedText.english("J/kg"), LocalizedText.english("joule per kilogram"));

    public static final EUInformation CODE_D95 = new EUInformation(CEFACT_NAMESPACE_URI, 4471093, LocalizedText.english("J/g"), LocalizedText.english("joule per gram"));

    public static final EUInformation CODE_JK = new EUInformation(CEFACT_NAMESPACE_URI, 19019, LocalizedText.english("MJ/kg"), LocalizedText.english("megajoule per kilogram"));

    public static final EUInformation CODE_B42 = new EUInformation(CEFACT_NAMESPACE_URI, 4338738, LocalizedText.english("kJ/kg"), LocalizedText.english("kilojoule per kilogram"));

    public static final EUInformation CODE_AZ = new EUInformation(CEFACT_NAMESPACE_URI, 16730, LocalizedText.english("BtuIT/lb"), LocalizedText.english("British thermal unit (international table) per pound"));

    public static final EUInformation CODE_D75 = new EUInformation(CEFACT_NAMESPACE_URI, 4470581, LocalizedText.english("calIT/g"), LocalizedText.english("calorie (international table) per gram"));

    public static final EUInformation CODE_N73 = new EUInformation(CEFACT_NAMESPACE_URI, 5125939, LocalizedText.english("Btuth/lb"), LocalizedText.english("British thermal unit (thermochemical) per pound"));

    public static final EUInformation CODE_B36 = new EUInformation(CEFACT_NAMESPACE_URI, 4338486, LocalizedText.english("calth/g"), LocalizedText.english("calorie (thermochemical) per gram"));

    public static final EUInformation CODE_N58 = new EUInformation(CEFACT_NAMESPACE_URI, 5125432, LocalizedText.english("BtuIT/ft³"), LocalizedText.english("British thermal unit (international table) per cubic foot"));

    public static final EUInformation CODE_N59 = new EUInformation(CEFACT_NAMESPACE_URI, 5125433, LocalizedText.english("Btuth/ft³"), LocalizedText.english("British thermal unit (thermochemical) per cubic foot"));

    public static final EUInformation CODE_Q31 = new EUInformation(CEFACT_NAMESPACE_URI, 5321521, LocalizedText.english("kJ/g"), LocalizedText.english("kilojoule per gram"));

    public static final EUInformation CODE_AMP = new EUInformation(CEFACT_NAMESPACE_URI, 4279632, LocalizedText.english("A"), LocalizedText.english("ampere"));

    public static final EUInformation CODE_B22 = new EUInformation(CEFACT_NAMESPACE_URI, 4338226, LocalizedText.english("kA"), LocalizedText.english("kiloampere"));

    public static final EUInformation CODE_H38 = new EUInformation(CEFACT_NAMESPACE_URI, 4731704, LocalizedText.english("MA"), LocalizedText.english("megaampere"));

    public static final EUInformation CODE_4K = new EUInformation(CEFACT_NAMESPACE_URI, 13387, LocalizedText.english("mA"), LocalizedText.english("milliampere"));

    public static final EUInformation CODE_B84 = new EUInformation(CEFACT_NAMESPACE_URI, 4339764, LocalizedText.english("µA"), LocalizedText.english("microampere"));

    public static final EUInformation CODE_C39 = new EUInformation(CEFACT_NAMESPACE_URI, 4404025, LocalizedText.english("nA"), LocalizedText.english("nanoampere"));

    public static final EUInformation CODE_C70 = new EUInformation(CEFACT_NAMESPACE_URI, 4405040, LocalizedText.english("pA"), LocalizedText.english("picoampere"));

    public static final EUInformation CODE_N96 = new EUInformation(CEFACT_NAMESPACE_URI, 5126454, LocalizedText.english("Bi"), LocalizedText.english("biot"));

    public static final EUInformation CODE_N97 = new EUInformation(CEFACT_NAMESPACE_URI, 5126455, LocalizedText.english("Gi"), LocalizedText.english("gilbert"));

    public static final EUInformation CODE_COU = new EUInformation(CEFACT_NAMESPACE_URI, 4411221, LocalizedText.english("C"), LocalizedText.english("coulomb"));

    public static final EUInformation CODE_A8 = new EUInformation(CEFACT_NAMESPACE_URI, 16696, LocalizedText.english("A·s"), LocalizedText.english("ampere second"));

    public static final EUInformation CODE_H32 = new EUInformation(CEFACT_NAMESPACE_URI, 4731698, LocalizedText.english("A²·s"), LocalizedText.english("ampere squared second"));

    public static final EUInformation CODE_AMH = new EUInformation(CEFACT_NAMESPACE_URI, 4279624, LocalizedText.english("A·h"), LocalizedText.english("ampere hour"));

    public static final EUInformation CODE_TAH = new EUInformation(CEFACT_NAMESPACE_URI, 5521736, LocalizedText.english("kA·h"), LocalizedText.english("kiloampere hour (thousand ampere hour)"));

    public static final EUInformation CODE_D77 = new EUInformation(CEFACT_NAMESPACE_URI, 4470583, LocalizedText.english("MC"), LocalizedText.english("megacoulomb"));

    public static final EUInformation CODE_D86 = new EUInformation(CEFACT_NAMESPACE_URI, 4470838, LocalizedText.english("mC"), LocalizedText.english("millicoulomb"));

    public static final EUInformation CODE_B26 = new EUInformation(CEFACT_NAMESPACE_URI, 4338230, LocalizedText.english("kC"), LocalizedText.english("kilocoulomb"));

    public static final EUInformation CODE_B86 = new EUInformation(CEFACT_NAMESPACE_URI, 4339766, LocalizedText.english("µC"), LocalizedText.english("microcoulomb"));

    public static final EUInformation CODE_C40 = new EUInformation(CEFACT_NAMESPACE_URI, 4404272, LocalizedText.english("nC"), LocalizedText.english("nanocoulomb"));

    public static final EUInformation CODE_C71 = new EUInformation(CEFACT_NAMESPACE_URI, 4405041, LocalizedText.english("pC"), LocalizedText.english("picocoulomb"));

    public static final EUInformation CODE_E09 = new EUInformation(CEFACT_NAMESPACE_URI, 4534329, LocalizedText.english("mA·h"), LocalizedText.english("milliampere hour"));

    public static final EUInformation CODE_N95 = new EUInformation(CEFACT_NAMESPACE_URI, 5126453, LocalizedText.english("A·min"), LocalizedText.english("ampere minute"));

    public static final EUInformation CODE_N94 = new EUInformation(CEFACT_NAMESPACE_URI, 5126452, LocalizedText.english("Fr"), LocalizedText.english("franklin"));

    public static final EUInformation CODE_A29 = new EUInformation(CEFACT_NAMESPACE_URI, 4272697, LocalizedText.english("C/m³"), LocalizedText.english("coulomb per cubic metre"));

    public static final EUInformation CODE_A84 = new EUInformation(CEFACT_NAMESPACE_URI, 4274228, LocalizedText.english("GC/m³"), LocalizedText.english("gigacoulomb per cubic metre"));

    public static final EUInformation CODE_A30 = new EUInformation(CEFACT_NAMESPACE_URI, 4272944, LocalizedText.english("C/mm³"), LocalizedText.english("coulomb per cubic millimetre"));

    public static final EUInformation CODE_B69 = new EUInformation(CEFACT_NAMESPACE_URI, 4339257, LocalizedText.english("MC/m³"), LocalizedText.english("megacoulomb per cubic metre"));

    public static final EUInformation CODE_A28 = new EUInformation(CEFACT_NAMESPACE_URI, 4272696, LocalizedText.english("C/cm³"), LocalizedText.english("coulomb per cubic centimetre"));

    public static final EUInformation CODE_B27 = new EUInformation(CEFACT_NAMESPACE_URI, 4338231, LocalizedText.english("kC/m³"), LocalizedText.english("kilocoulomb per cubic metre"));

    public static final EUInformation CODE_D88 = new EUInformation(CEFACT_NAMESPACE_URI, 4470840, LocalizedText.english("mC/m³"), LocalizedText.english("millicoulomb per cubic metre"));

    public static final EUInformation CODE_B87 = new EUInformation(CEFACT_NAMESPACE_URI, 4339767, LocalizedText.english("µC/m³"), LocalizedText.english("microcoulomb per cubic metre"));

    public static final EUInformation CODE_A34 = new EUInformation(CEFACT_NAMESPACE_URI, 4272948, LocalizedText.english("C/m²"), LocalizedText.english("coulomb per square metre"));

    public static final EUInformation CODE_B70 = new EUInformation(CEFACT_NAMESPACE_URI, 4339504, LocalizedText.english("MC/m²"), LocalizedText.english("megacoulomb per square metre"));

    public static final EUInformation CODE_A35 = new EUInformation(CEFACT_NAMESPACE_URI, 4272949, LocalizedText.english("C/mm²"), LocalizedText.english("coulomb per square millimetre"));

    public static final EUInformation CODE_A33 = new EUInformation(CEFACT_NAMESPACE_URI, 4272947, LocalizedText.english("C/cm²"), LocalizedText.english("coulomb per square centimetre"));

    public static final EUInformation CODE_B28 = new EUInformation(CEFACT_NAMESPACE_URI, 4338232, LocalizedText.english("kC/m²"), LocalizedText.english("kilocoulomb per square metre"));

    public static final EUInformation CODE_D89 = new EUInformation(CEFACT_NAMESPACE_URI, 4470841, LocalizedText.english("mC/m²"), LocalizedText.english("millicoulomb per square metre"));

    public static final EUInformation CODE_B88 = new EUInformation(CEFACT_NAMESPACE_URI, 4339768, LocalizedText.english("µC/m²"), LocalizedText.english("microcoulomb per square metre"));

    public static final EUInformation CODE_D50 = new EUInformation(CEFACT_NAMESPACE_URI, 4470064, LocalizedText.english("V/m"), LocalizedText.english("volt per metre"));

    public static final EUInformation CODE_H45 = new EUInformation(CEFACT_NAMESPACE_URI, 4731957, LocalizedText.english("V·s/m"), LocalizedText.english("volt second per metre"));

    public static final EUInformation CODE_D45 = new EUInformation(CEFACT_NAMESPACE_URI, 4469813, LocalizedText.english("V²/K²"), LocalizedText.english("volt squared per kelvin squared"));

    public static final EUInformation CODE_D51 = new EUInformation(CEFACT_NAMESPACE_URI, 4470065, LocalizedText.english("V/mm"), LocalizedText.english("volt per millimetre"));

    public static final EUInformation CODE_H24 = new EUInformation(CEFACT_NAMESPACE_URI, 4731444, LocalizedText.english("V/µs"), LocalizedText.english("volt per microsecond"));

    public static final EUInformation CODE_H62 = new EUInformation(CEFACT_NAMESPACE_URI, 4732466, LocalizedText.english("mV/min"), LocalizedText.english("millivolt per minute"));

    public static final EUInformation CODE_H46 = new EUInformation(CEFACT_NAMESPACE_URI, 4731958, LocalizedText.english("V/s"), LocalizedText.english("volt per second"));

    public static final EUInformation CODE_B79 = new EUInformation(CEFACT_NAMESPACE_URI, 4339513, LocalizedText.english("MV/m"), LocalizedText.english("megavolt per metre"));

    public static final EUInformation CODE_B55 = new EUInformation(CEFACT_NAMESPACE_URI, 4338997, LocalizedText.english("kV/m"), LocalizedText.english("kilovolt per metre"));

    public static final EUInformation CODE_D47 = new EUInformation(CEFACT_NAMESPACE_URI, 4469815, LocalizedText.english("V/cm"), LocalizedText.english("volt per centimetre"));

    public static final EUInformation CODE_C30 = new EUInformation(CEFACT_NAMESPACE_URI, 4404016, LocalizedText.english("mV/m"), LocalizedText.english("millivolt per metre"));

    public static final EUInformation CODE_C3 = new EUInformation(CEFACT_NAMESPACE_URI, 17203, LocalizedText.english("µV/m"), LocalizedText.english("microvolt per metre"));

    public static final EUInformation CODE_G60 = new EUInformation(CEFACT_NAMESPACE_URI, 4666928, LocalizedText.english("V/bar"), LocalizedText.english("volt per bar"));

    public static final EUInformation CODE_N98 = new EUInformation(CEFACT_NAMESPACE_URI, 5126456, LocalizedText.english("V/Pa"), LocalizedText.english("volt per pascal"));

    public static final EUInformation CODE_F87 = new EUInformation(CEFACT_NAMESPACE_URI, 4601911, LocalizedText.english("V/(l·min)"), LocalizedText.english("volt per litre minute"));

    public static final EUInformation CODE_H22 = new EUInformation(CEFACT_NAMESPACE_URI, 4731442, LocalizedText.english("V/(lbf/in²)"), LocalizedText.english("volt square inch per pound-force"));

    public static final EUInformation CODE_H23 = new EUInformation(CEFACT_NAMESPACE_URI, 4731443, LocalizedText.english("V/in"), LocalizedText.english("volt per inch"));

    public static final EUInformation CODE_VLT = new EUInformation(CEFACT_NAMESPACE_URI, 5655636, LocalizedText.english("V"), LocalizedText.english("volt"));

    public static final EUInformation CODE_B78 = new EUInformation(CEFACT_NAMESPACE_URI, 4339512, LocalizedText.english("MV"), LocalizedText.english("megavolt"));

    public static final EUInformation CODE_KVT = new EUInformation(CEFACT_NAMESPACE_URI, 4937300, LocalizedText.english("kV"), LocalizedText.english("kilovolt"));

    public static final EUInformation CODE_2Z = new EUInformation(CEFACT_NAMESPACE_URI, 12890, LocalizedText.english("mV"), LocalizedText.english("millivolt"));

    public static final EUInformation CODE_D82 = new EUInformation(CEFACT_NAMESPACE_URI, 4470834, LocalizedText.english("µV"), LocalizedText.english("microvolt"));

    public static final EUInformation CODE_N99 = new EUInformation(CEFACT_NAMESPACE_URI, 5126457, LocalizedText.english("pV"), LocalizedText.english("picovolt"));

    public static final EUInformation CODE_FAR = new EUInformation(CEFACT_NAMESPACE_URI, 4604242, LocalizedText.english("F"), LocalizedText.english("farad"));

    public static final EUInformation CODE_H48 = new EUInformation(CEFACT_NAMESPACE_URI, 4731960, LocalizedText.english("aF"), LocalizedText.english("attofarad"));

    public static final EUInformation CODE_C10 = new EUInformation(CEFACT_NAMESPACE_URI, 4403504, LocalizedText.english("mF"), LocalizedText.english("millifarad"));

    public static final EUInformation CODE_4O = new EUInformation(CEFACT_NAMESPACE_URI, 13391, LocalizedText.english("µF"), LocalizedText.english("microfarad"));

    public static final EUInformation CODE_C41 = new EUInformation(CEFACT_NAMESPACE_URI, 4404273, LocalizedText.english("nF"), LocalizedText.english("nanofarad"));

    public static final EUInformation CODE_4T = new EUInformation(CEFACT_NAMESPACE_URI, 13396, LocalizedText.english("pF"), LocalizedText.english("picofarad"));

    public static final EUInformation CODE_N90 = new EUInformation(CEFACT_NAMESPACE_URI, 5126448, LocalizedText.english("kF"), LocalizedText.english("kilofarad"));

    public static final EUInformation CODE_A69 = new EUInformation(CEFACT_NAMESPACE_URI, 4273721, LocalizedText.english("F/m"), LocalizedText.english("farad per metre"));

    public static final EUInformation CODE_H28 = new EUInformation(CEFACT_NAMESPACE_URI, 4731448, LocalizedText.english("µF/km"), LocalizedText.english("microfarad per kilometre"));

    public static final EUInformation CODE_H33 = new EUInformation(CEFACT_NAMESPACE_URI, 4731699, LocalizedText.english("F/km"), LocalizedText.english("farad per kilometre"));

    public static final EUInformation CODE_B89 = new EUInformation(CEFACT_NAMESPACE_URI, 4339769, LocalizedText.english("µF/m"), LocalizedText.english("microfarad per metre"));

    public static final EUInformation CODE_C42 = new EUInformation(CEFACT_NAMESPACE_URI, 4404274, LocalizedText.english("nF/m"), LocalizedText.english("nanofarad per metre"));

    public static final EUInformation CODE_C72 = new EUInformation(CEFACT_NAMESPACE_URI, 4405042, LocalizedText.english("pF/m"), LocalizedText.english("picofarad per metre"));

    public static final EUInformation CODE_A26 = new EUInformation(CEFACT_NAMESPACE_URI, 4272694, LocalizedText.english("C·m"), LocalizedText.english("coulomb metre"));

    public static final EUInformation CODE_A41 = new EUInformation(CEFACT_NAMESPACE_URI, 4273201, LocalizedText.english("A/m²"), LocalizedText.english("ampere per square metre"));

    public static final EUInformation CODE_H31 = new EUInformation(CEFACT_NAMESPACE_URI, 4731697, LocalizedText.english("A/kg"), LocalizedText.english("ampere per kilogram"));

    public static final EUInformation CODE_B66 = new EUInformation(CEFACT_NAMESPACE_URI, 4339254, LocalizedText.english("MA/m²"), LocalizedText.english("megaampere per square metre"));

    public static final EUInformation CODE_A7 = new EUInformation(CEFACT_NAMESPACE_URI, 16695, LocalizedText.english("A/mm²"), LocalizedText.english("ampere per square millimetre"));

    public static final EUInformation CODE_A4 = new EUInformation(CEFACT_NAMESPACE_URI, 16692, LocalizedText.english("A/cm²"), LocalizedText.english("ampere per square centimetre"));

    public static final EUInformation CODE_B23 = new EUInformation(CEFACT_NAMESPACE_URI, 4338227, LocalizedText.english("kA/m²"), LocalizedText.english("kiloampere per square metre"));

    public static final EUInformation CODE_G59 = new EUInformation(CEFACT_NAMESPACE_URI, 4666681, LocalizedText.english("mA/(l·min)"), LocalizedText.english("milliampere per litre minute"));

    public static final EUInformation CODE_N93 = new EUInformation(CEFACT_NAMESPACE_URI, 5126451, LocalizedText.english("A/Pa"), LocalizedText.english("ampere per pascal"));

    public static final EUInformation CODE_F57 = new EUInformation(CEFACT_NAMESPACE_URI, 4601143, LocalizedText.english("mA/(lbf/in²)"), LocalizedText.english("milliampere per pound-force per square inch"));

    public static final EUInformation CODE_F59 = new EUInformation(CEFACT_NAMESPACE_URI, 4601145, LocalizedText.english("mA/bar"), LocalizedText.english("milliampere per bar"));

    public static final EUInformation CODE_AE = new EUInformation(CEFACT_NAMESPACE_URI, 16709, LocalizedText.english("A/m"), LocalizedText.english("ampere per metre"));

    public static final EUInformation CODE_B24 = new EUInformation(CEFACT_NAMESPACE_URI, 4338228, LocalizedText.english("kA/m"), LocalizedText.english("kiloampere per metre"));

    public static final EUInformation CODE_A3 = new EUInformation(CEFACT_NAMESPACE_URI, 16691, LocalizedText.english("A/mm"), LocalizedText.english("ampere per millimetre"));

    public static final EUInformation CODE_A2 = new EUInformation(CEFACT_NAMESPACE_URI, 16690, LocalizedText.english("A/cm"), LocalizedText.english("ampere per centimetre"));

    public static final EUInformation CODE_F76 = new EUInformation(CEFACT_NAMESPACE_URI, 4601654, LocalizedText.english("mA/mm"), LocalizedText.english("milliampere per millimetre"));

    public static final EUInformation CODE_F08 = new EUInformation(CEFACT_NAMESPACE_URI, 4599864, LocalizedText.english("mA/in"), LocalizedText.english("milliampere per inch"));

    public static final EUInformation CODE_P10 = new EUInformation(CEFACT_NAMESPACE_URI, 5255472, LocalizedText.english("C/m"), LocalizedText.english("coulomb per metre"));

    public static final EUInformation CODE_D33 = new EUInformation(CEFACT_NAMESPACE_URI, 4469555, LocalizedText.english("T"), LocalizedText.english("tesla"));

    public static final EUInformation CODE_C29 = new EUInformation(CEFACT_NAMESPACE_URI, 4403769, LocalizedText.english("mT"), LocalizedText.english("millitesla"));

    public static final EUInformation CODE_D81 = new EUInformation(CEFACT_NAMESPACE_URI, 4470833, LocalizedText.english("µT"), LocalizedText.english("microtesla"));

    public static final EUInformation CODE_C48 = new EUInformation(CEFACT_NAMESPACE_URI, 4404280, LocalizedText.english("nT"), LocalizedText.english("nanotesla"));

    public static final EUInformation CODE_P13 = new EUInformation(CEFACT_NAMESPACE_URI, 5255475, LocalizedText.english("kT"), LocalizedText.english("kilotesla"));

    public static final EUInformation CODE_P12 = new EUInformation(CEFACT_NAMESPACE_URI, 5255474, LocalizedText.english("γ"), LocalizedText.english("gamma"));

    public static final EUInformation CODE_WEB = new EUInformation(CEFACT_NAMESPACE_URI, 5719362, LocalizedText.english("Wb"), LocalizedText.english("weber"));

    public static final EUInformation CODE_C33 = new EUInformation(CEFACT_NAMESPACE_URI, 4404019, LocalizedText.english("mWb"), LocalizedText.english("milliweber"));

    public static final EUInformation CODE_P11 = new EUInformation(CEFACT_NAMESPACE_URI, 5255473, LocalizedText.english("kWb"), LocalizedText.english("kiloweber"));

    public static final EUInformation CODE_D59 = new EUInformation(CEFACT_NAMESPACE_URI, 4470073, LocalizedText.english("Wb/m"), LocalizedText.english("weber per metre"));

    public static final EUInformation CODE_B56 = new EUInformation(CEFACT_NAMESPACE_URI, 4338998, LocalizedText.english("kWb/m"), LocalizedText.english("kiloweber per metre"));

    public static final EUInformation CODE_D60 = new EUInformation(CEFACT_NAMESPACE_URI, 4470320, LocalizedText.english("Wb/mm"), LocalizedText.english("weber per millimetre"));

    public static final EUInformation CODE_81 = new EUInformation(CEFACT_NAMESPACE_URI, 14385, LocalizedText.english("H"), LocalizedText.english("henry"));

    public static final EUInformation CODE_C14 = new EUInformation(CEFACT_NAMESPACE_URI, 4403508, LocalizedText.english("mH"), LocalizedText.english("millihenry"));

    public static final EUInformation CODE_B90 = new EUInformation(CEFACT_NAMESPACE_URI, 4340016, LocalizedText.english("µH"), LocalizedText.english("microhenry"));

    public static final EUInformation CODE_C43 = new EUInformation(CEFACT_NAMESPACE_URI, 4404275, LocalizedText.english("nH"), LocalizedText.english("nanohenry"));

    public static final EUInformation CODE_C73 = new EUInformation(CEFACT_NAMESPACE_URI, 4405043, LocalizedText.english("pH"), LocalizedText.english("picohenry"));

    public static final EUInformation CODE_H03 = new EUInformation(CEFACT_NAMESPACE_URI, 4730931, LocalizedText.english("H/kΩ"), LocalizedText.english("henry per kiloohm"));

    public static final EUInformation CODE_H04 = new EUInformation(CEFACT_NAMESPACE_URI, 4730932, LocalizedText.english("H/Ω"), LocalizedText.english("henry per ohm"));

    public static final EUInformation CODE_G98 = new EUInformation(CEFACT_NAMESPACE_URI, 4667704, LocalizedText.english("µH/kΩ"), LocalizedText.english("microhenry per kiloohm"));

    public static final EUInformation CODE_G99 = new EUInformation(CEFACT_NAMESPACE_URI, 4667705, LocalizedText.english("µH/Ω"), LocalizedText.english("microhenry per ohm"));

    public static final EUInformation CODE_H05 = new EUInformation(CEFACT_NAMESPACE_URI, 4730933, LocalizedText.english("mH/kΩ"), LocalizedText.english("millihenry per kiloohm"));

    public static final EUInformation CODE_H06 = new EUInformation(CEFACT_NAMESPACE_URI, 4730934, LocalizedText.english("mH/Ω"), LocalizedText.english("millihenry per ohm"));

    public static final EUInformation CODE_P24 = new EUInformation(CEFACT_NAMESPACE_URI, 5255732, LocalizedText.english("kH"), LocalizedText.english("kilohenry"));

    public static final EUInformation CODE_A98 = new EUInformation(CEFACT_NAMESPACE_URI, 4274488, LocalizedText.english("H/m"), LocalizedText.english("henry per metre"));

    public static final EUInformation CODE_B91 = new EUInformation(CEFACT_NAMESPACE_URI, 4340017, LocalizedText.english("µH/m"), LocalizedText.english("microhenry per metre"));

    public static final EUInformation CODE_C44 = new EUInformation(CEFACT_NAMESPACE_URI, 4404276, LocalizedText.english("nH/m"), LocalizedText.english("nanohenry per metre"));

    public static final EUInformation CODE_A5 = new EUInformation(CEFACT_NAMESPACE_URI, 16693, LocalizedText.english("A·m²"), LocalizedText.english("ampere square metre"));

    public static final EUInformation CODE_B8 = new EUInformation(CEFACT_NAMESPACE_URI, 16952, LocalizedText.english("J/m³"), LocalizedText.english("joule per cubic metre"));

    public static final EUInformation CODE_OHM = new EUInformation(CEFACT_NAMESPACE_URI, 5195853, LocalizedText.english("Ω"), LocalizedText.english("ohm"));

    public static final EUInformation CODE_A87 = new EUInformation(CEFACT_NAMESPACE_URI, 4274231, LocalizedText.english("GΩ"), LocalizedText.english("gigaohm"));

    public static final EUInformation CODE_B75 = new EUInformation(CEFACT_NAMESPACE_URI, 4339509, LocalizedText.english("MΩ"), LocalizedText.english("megaohm"));

    public static final EUInformation CODE_H44 = new EUInformation(CEFACT_NAMESPACE_URI, 4731956, LocalizedText.english("TΩ"), LocalizedText.english("teraohm"));

    public static final EUInformation CODE_B49 = new EUInformation(CEFACT_NAMESPACE_URI, 4338745, LocalizedText.english("kΩ"), LocalizedText.english("kiloohm"));

    public static final EUInformation CODE_E45 = new EUInformation(CEFACT_NAMESPACE_URI, 4535349, LocalizedText.english("mΩ"), LocalizedText.english("milliohm"));

    public static final EUInformation CODE_B94 = new EUInformation(CEFACT_NAMESPACE_URI, 4340020, LocalizedText.english("µΩ"), LocalizedText.english("microohm"));

    public static final EUInformation CODE_P22 = new EUInformation(CEFACT_NAMESPACE_URI, 5255730, LocalizedText.english("nΩ"), LocalizedText.english("nanoohm"));

    public static final EUInformation CODE_M26 = new EUInformation(CEFACT_NAMESPACE_URI, 5059126, LocalizedText.english("GΩ/m"), LocalizedText.english("gigaohm per metre"));

    public static final EUInformation CODE_SIE = new EUInformation(CEFACT_NAMESPACE_URI, 5458245, LocalizedText.english("S"), LocalizedText.english("siemens"));

    public static final EUInformation CODE_B53 = new EUInformation(CEFACT_NAMESPACE_URI, 4338995, LocalizedText.english("kS"), LocalizedText.english("kilosiemens"));

    public static final EUInformation CODE_C27 = new EUInformation(CEFACT_NAMESPACE_URI, 4403767, LocalizedText.english("mS"), LocalizedText.english("millisiemens"));

    public static final EUInformation CODE_B99 = new EUInformation(CEFACT_NAMESPACE_URI, 4340025, LocalizedText.english("µS"), LocalizedText.english("microsiemens"));

    public static final EUInformation CODE_G42 = new EUInformation(CEFACT_NAMESPACE_URI, 4666418, LocalizedText.english("µS/cm"), LocalizedText.english("microsiemens per centimetre"));

    public static final EUInformation CODE_G43 = new EUInformation(CEFACT_NAMESPACE_URI, 4666419, LocalizedText.english("µS/m"), LocalizedText.english("microsiemens per metre"));

    public static final EUInformation CODE_N92 = new EUInformation(CEFACT_NAMESPACE_URI, 5126450, LocalizedText.english("pS"), LocalizedText.english("picosiemens"));

    public static final EUInformation CODE_C61 = new EUInformation(CEFACT_NAMESPACE_URI, 4404785, LocalizedText.english("Ω·m"), LocalizedText.english("ohm metre"));

    public static final EUInformation CODE_A88 = new EUInformation(CEFACT_NAMESPACE_URI, 4274232, LocalizedText.english("GΩ·m"), LocalizedText.english("gigaohm metre"));

    public static final EUInformation CODE_B76 = new EUInformation(CEFACT_NAMESPACE_URI, 4339510, LocalizedText.english("MΩ·m"), LocalizedText.english("megaohm metre"));

    public static final EUInformation CODE_H88 = new EUInformation(CEFACT_NAMESPACE_URI, 4732984, LocalizedText.english("MΩ·km"), LocalizedText.english("megaohm kilometre"));

    public static final EUInformation CODE_B50 = new EUInformation(CEFACT_NAMESPACE_URI, 4338992, LocalizedText.english("kΩ·m"), LocalizedText.english("kiloohm metre"));

    public static final EUInformation CODE_C60 = new EUInformation(CEFACT_NAMESPACE_URI, 4404784, LocalizedText.english("Ω·cm"), LocalizedText.english("ohm centimetre"));

    public static final EUInformation CODE_C23 = new EUInformation(CEFACT_NAMESPACE_URI, 4403763, LocalizedText.english("mΩ·m"), LocalizedText.english("milliohm metre"));

    public static final EUInformation CODE_B95 = new EUInformation(CEFACT_NAMESPACE_URI, 4340021, LocalizedText.english("µΩ·m"), LocalizedText.english("microohm metre"));

    public static final EUInformation CODE_C46 = new EUInformation(CEFACT_NAMESPACE_URI, 4404278, LocalizedText.english("nΩ·m"), LocalizedText.english("nanoohm metre"));

    public static final EUInformation CODE_M24 = new EUInformation(CEFACT_NAMESPACE_URI, 5059124, LocalizedText.english("Ω·km"), LocalizedText.english("ohm kilometre"));

    public static final EUInformation CODE_P23 = new EUInformation(CEFACT_NAMESPACE_URI, 5255731, LocalizedText.english("Ω·cmil/ft"), LocalizedText.english("ohm circular-mil per foot"));

    public static final EUInformation CODE_F56 = new EUInformation(CEFACT_NAMESPACE_URI, 4601142, LocalizedText.english("Ω/km"), LocalizedText.english("ohm per kilometre"));

    public static final EUInformation CODE_H26 = new EUInformation(CEFACT_NAMESPACE_URI, 4731446, LocalizedText.english("Ω/m"), LocalizedText.english("ohm per metre"));

    public static final EUInformation CODE_H37 = new EUInformation(CEFACT_NAMESPACE_URI, 4731703, LocalizedText.english("MΩ/m"), LocalizedText.english("megaohm per metre"));

    public static final EUInformation CODE_F54 = new EUInformation(CEFACT_NAMESPACE_URI, 4601140, LocalizedText.english("mΩ/m"), LocalizedText.english("milliohm per metre"));

    public static final EUInformation CODE_H36 = new EUInformation(CEFACT_NAMESPACE_URI, 4731702, LocalizedText.english("MΩ/km"), LocalizedText.english("megaohm per kilometre"));

    public static final EUInformation CODE_F55 = new EUInformation(CEFACT_NAMESPACE_URI, 4601141, LocalizedText.english("Ω/mi"), LocalizedText.english("ohm per mile (statute mile)"));

    public static final EUInformation CODE_D10 = new EUInformation(CEFACT_NAMESPACE_URI, 4469040, LocalizedText.english("S/m"), LocalizedText.english("siemens per metre"));

    public static final EUInformation CODE_H43 = new EUInformation(CEFACT_NAMESPACE_URI, 4731955, LocalizedText.english("S/cm"), LocalizedText.english("siemens per centimetre"));

    public static final EUInformation CODE_H61 = new EUInformation(CEFACT_NAMESPACE_URI, 4732465, LocalizedText.english("mS/cm"), LocalizedText.english("millisiemens per centimetre"));

    public static final EUInformation CODE_B77 = new EUInformation(CEFACT_NAMESPACE_URI, 4339511, LocalizedText.english("MS/m"), LocalizedText.english("megasiemens per metre"));

    public static final EUInformation CODE_B54 = new EUInformation(CEFACT_NAMESPACE_URI, 4338996, LocalizedText.english("kS/m"), LocalizedText.english("kilosiemens per metre"));

    public static final EUInformation CODE_G45 = new EUInformation(CEFACT_NAMESPACE_URI, 4666421, LocalizedText.english("nS/m"), LocalizedText.english("nanosiemens per metre"));

    public static final EUInformation CODE_G44 = new EUInformation(CEFACT_NAMESPACE_URI, 4666420, LocalizedText.english("nS/cm"), LocalizedText.english("nanosiemens per centimetre"));

    public static final EUInformation CODE_L42 = new EUInformation(CEFACT_NAMESPACE_URI, 4994098, LocalizedText.english("pS/m"), LocalizedText.english("picosiemens per metre"));

    public static final EUInformation CODE_C89 = new EUInformation(CEFACT_NAMESPACE_URI, 4405305, LocalizedText.english("H⁻¹"), LocalizedText.english("reciprocal henry"));

    public static final EUInformation CODE_P14 = new EUInformation(CEFACT_NAMESPACE_URI, 5255476, LocalizedText.english("J/s"), LocalizedText.english("joule per second"));

    public static final EUInformation CODE_D31 = new EUInformation(CEFACT_NAMESPACE_URI, 4469553, LocalizedText.english("TW"), LocalizedText.english("terawatt"));

    public static final EUInformation CODE_P15 = new EUInformation(CEFACT_NAMESPACE_URI, 5255477, LocalizedText.english("J/min"), LocalizedText.english("joule per minute"));

    public static final EUInformation CODE_P16 = new EUInformation(CEFACT_NAMESPACE_URI, 5255478, LocalizedText.english("J/h"), LocalizedText.english("joule per hour"));

    public static final EUInformation CODE_P17 = new EUInformation(CEFACT_NAMESPACE_URI, 5255479, LocalizedText.english("J/d"), LocalizedText.english("joule per day"));

    public static final EUInformation CODE_P18 = new EUInformation(CEFACT_NAMESPACE_URI, 5255480, LocalizedText.english("kJ/s"), LocalizedText.english("kilojoule per second"));

    public static final EUInformation CODE_P19 = new EUInformation(CEFACT_NAMESPACE_URI, 5255481, LocalizedText.english("kJ/min"), LocalizedText.english("kilojoule per minute"));

    public static final EUInformation CODE_P20 = new EUInformation(CEFACT_NAMESPACE_URI, 5255728, LocalizedText.english("kJ/h"), LocalizedText.english("kilojoule per hour"));

    public static final EUInformation CODE_P21 = new EUInformation(CEFACT_NAMESPACE_URI, 5255729, LocalizedText.english("kJ/d"), LocalizedText.english("kilojoule per day"));

    public static final EUInformation CODE_K43 = new EUInformation(CEFACT_NAMESPACE_URI, 4928563, LocalizedText.english("electric hp"), LocalizedText.english("horsepower (electric)"));

    public static final EUInformation CODE_C49 = new EUInformation(CEFACT_NAMESPACE_URI, 4404281, LocalizedText.english("nW"), LocalizedText.english("nanowatt"));

    public static final EUInformation CODE_C75 = new EUInformation(CEFACT_NAMESPACE_URI, 4405045, LocalizedText.english("pW"), LocalizedText.english("picowatt"));

    public static final EUInformation CODE_D46 = new EUInformation(CEFACT_NAMESPACE_URI, 4469814, LocalizedText.english("V·A"), LocalizedText.english("volt - ampere"));

    public static final EUInformation CODE_MVA = new EUInformation(CEFACT_NAMESPACE_URI, 5068353, LocalizedText.english("MV·A"), LocalizedText.english("megavolt - ampere"));

    public static final EUInformation CODE_KVA = new EUInformation(CEFACT_NAMESPACE_URI, 4937281, LocalizedText.english("kV·A"), LocalizedText.english("kilovolt - ampere"));

    public static final EUInformation CODE_M35 = new EUInformation(CEFACT_NAMESPACE_URI, 5059381, LocalizedText.english("mV·A"), LocalizedText.english("millivolt - ampere"));

    public static final EUInformation CODE_D44 = new EUInformation(CEFACT_NAMESPACE_URI, 4469812, LocalizedText.english("var"), LocalizedText.english("var"));

    public static final EUInformation CODE_K5 = new EUInformation(CEFACT_NAMESPACE_URI, 19253, LocalizedText.english("kvar"), LocalizedText.english("kilovolt ampere (reactive)"));

    public static final EUInformation CODE_KVR = new EUInformation(CEFACT_NAMESPACE_URI, 4937298, LocalizedText.english("kvar"), LocalizedText.english("kilovar"));

    public static final EUInformation CODE_MAR = new EUInformation(CEFACT_NAMESPACE_URI, 5062994, LocalizedText.english("mvar"), LocalizedText.english("megavar"));

    public static final EUInformation CODE_N91 = new EUInformation(CEFACT_NAMESPACE_URI, 5126449, LocalizedText.english("1/J"), LocalizedText.english("reciprocal joule"));

    public static final EUInformation CODE_M30 = new EUInformation(CEFACT_NAMESPACE_URI, 5059376, LocalizedText.english("1/(V·A·s)"), LocalizedText.english("reciprocal volt - ampere reciprocal second"));

    public static final EUInformation CODE_M17 = new EUInformation(CEFACT_NAMESPACE_URI, 5058871, LocalizedText.english("kHz·m"), LocalizedText.english("kilohertz metre"));

    public static final EUInformation CODE_M18 = new EUInformation(CEFACT_NAMESPACE_URI, 5058872, LocalizedText.english("GHz·m"), LocalizedText.english("gigahertz metre"));

    public static final EUInformation CODE_M27 = new EUInformation(CEFACT_NAMESPACE_URI, 5059127, LocalizedText.english("MHz·m"), LocalizedText.english("megahertz metre"));

    public static final EUInformation CODE_M21 = new EUInformation(CEFACT_NAMESPACE_URI, 5059121, LocalizedText.english("1/kVAh"), LocalizedText.english("reciprocal kilovolt - ampere reciprocal hour"));

    public static final EUInformation CODE_H34 = new EUInformation(CEFACT_NAMESPACE_URI, 4731700, LocalizedText.english("Hz·m"), LocalizedText.english("hertz metre"));

    public static final EUInformation CODE_H39 = new EUInformation(CEFACT_NAMESPACE_URI, 4731705, LocalizedText.english("MHz·km"), LocalizedText.english("megahertz kilometre"));

    public static final EUInformation CODE_C84 = new EUInformation(CEFACT_NAMESPACE_URI, 4405300, LocalizedText.english("rad/m"), LocalizedText.english("radian per metre"));

    public static final EUInformation CODE_JM = new EUInformation(CEFACT_NAMESPACE_URI, 19021, LocalizedText.english("MJ/m³"), LocalizedText.english("megajoule per cubic metre"));

    public static final EUInformation CODE_B14 = new EUInformation(CEFACT_NAMESPACE_URI, 4337972, LocalizedText.english("J/m⁴"), LocalizedText.english("joule per metre to the fourth power"));

    public static final EUInformation CODE_B13 = new EUInformation(CEFACT_NAMESPACE_URI, 4337971, LocalizedText.english("J/m²"), LocalizedText.english("joule per square metre"));

    public static final EUInformation CODE_D1 = new EUInformation(CEFACT_NAMESPACE_URI, 17457, LocalizedText.english("s⁻¹/sr"), LocalizedText.english("reciprocal second per steradian"));

    public static final EUInformation CODE_D2 = new EUInformation(CEFACT_NAMESPACE_URI, 17458, LocalizedText.english("s⁻¹/(sr·m²)"), LocalizedText.english("reciprocal second per steradian metre squared"));

    public static final EUInformation CODE_C99 = new EUInformation(CEFACT_NAMESPACE_URI, 4405561, LocalizedText.english("s⁻¹/m²"), LocalizedText.english("reciprocal second per metre squared"));

    public static final EUInformation CODE_C93 = new EUInformation(CEFACT_NAMESPACE_URI, 4405555, LocalizedText.english("m⁻²"), LocalizedText.english("reciprocal square metre"));

    public static final EUInformation CODE_H47 = new EUInformation(CEFACT_NAMESPACE_URI, 4731959, LocalizedText.english("W/m³"), LocalizedText.english("watt per cubic metre"));

    public static final EUInformation CODE_H74 = new EUInformation(CEFACT_NAMESPACE_URI, 4732724, LocalizedText.english("W/m"), LocalizedText.english("watt per metre"));

    public static final EUInformation CODE_E43 = new EUInformation(CEFACT_NAMESPACE_URI, 4535347, LocalizedText.english("J/cm²"), LocalizedText.english("joule per square centimetre"));

    public static final EUInformation CODE_P37 = new EUInformation(CEFACT_NAMESPACE_URI, 5255991, LocalizedText.english("BtuIT/ft²"), LocalizedText.english("British thermal unit (international table) per square foot"));

    public static final EUInformation CODE_P38 = new EUInformation(CEFACT_NAMESPACE_URI, 5255992, LocalizedText.english("Btuth/ft²"), LocalizedText.english("British thermal unit (thermochemical) per square foot"));

    public static final EUInformation CODE_P39 = new EUInformation(CEFACT_NAMESPACE_URI, 5255993, LocalizedText.english("calth/cm²"), LocalizedText.english("calorie (thermochemical) per square centimetre"));

    public static final EUInformation CODE_P40 = new EUInformation(CEFACT_NAMESPACE_URI, 5256240, LocalizedText.english("Ly"), LocalizedText.english("langley"));

    public static final EUInformation CODE_D57 = new EUInformation(CEFACT_NAMESPACE_URI, 4470071, LocalizedText.english("W/sr"), LocalizedText.english("watt per steradian"));

    public static final EUInformation CODE_D58 = new EUInformation(CEFACT_NAMESPACE_URI, 4470072, LocalizedText.english("W/(sr·m²)"), LocalizedText.english("watt per steradian square metre"));

    public static final EUInformation CODE_D56 = new EUInformation(CEFACT_NAMESPACE_URI, 4470070, LocalizedText.english("W/(m²·K⁴)"), LocalizedText.english("watt per square metre kelvin to the fourth power"));

    public static final EUInformation CODE_D18 = new EUInformation(CEFACT_NAMESPACE_URI, 4469048, LocalizedText.english("m·K"), LocalizedText.english("metre kelvin"));

    public static final EUInformation CODE_CDL = new EUInformation(CEFACT_NAMESPACE_URI, 4408396, LocalizedText.english("cd"), LocalizedText.english("candela"));

    public static final EUInformation CODE_P33 = new EUInformation(CEFACT_NAMESPACE_URI, 5255987, LocalizedText.english("kcd"), LocalizedText.english("kilocandela"));

    public static final EUInformation CODE_P34 = new EUInformation(CEFACT_NAMESPACE_URI, 5255988, LocalizedText.english("mcd"), LocalizedText.english("millicandela"));

    public static final EUInformation CODE_P35 = new EUInformation(CEFACT_NAMESPACE_URI, 5255989, LocalizedText.english("HK"), LocalizedText.english("Hefner-Kerze"));

    public static final EUInformation CODE_P36 = new EUInformation(CEFACT_NAMESPACE_URI, 5255990, LocalizedText.english("IK"), LocalizedText.english("international candle"));

    public static final EUInformation CODE_LUM = new EUInformation(CEFACT_NAMESPACE_URI, 5002573, LocalizedText.english("lm"), LocalizedText.english("lumen"));

    public static final EUInformation CODE_B62 = new EUInformation(CEFACT_NAMESPACE_URI, 4339250, LocalizedText.english("lm·s"), LocalizedText.english("lumen second"));

    public static final EUInformation CODE_B59 = new EUInformation(CEFACT_NAMESPACE_URI, 4339001, LocalizedText.english("lm·h"), LocalizedText.english("lumen hour"));

    public static final EUInformation CODE_A24 = new EUInformation(CEFACT_NAMESPACE_URI, 4272692, LocalizedText.english("cd/m²"), LocalizedText.english("candela per square metre"));

    public static final EUInformation CODE_P28 = new EUInformation(CEFACT_NAMESPACE_URI, 5255736, LocalizedText.english("cd/in²"), LocalizedText.english("candela per square inch"));

    public static final EUInformation CODE_P29 = new EUInformation(CEFACT_NAMESPACE_URI, 5255737, LocalizedText.english("ftL"), LocalizedText.english("footlambert"));

    public static final EUInformation CODE_P30 = new EUInformation(CEFACT_NAMESPACE_URI, 5255984, LocalizedText.english("Lb"), LocalizedText.english("lambert"));

    public static final EUInformation CODE_P31 = new EUInformation(CEFACT_NAMESPACE_URI, 5255985, LocalizedText.english("sb"), LocalizedText.english("stilb"));

    public static final EUInformation CODE_P32 = new EUInformation(CEFACT_NAMESPACE_URI, 5255986, LocalizedText.english("cd/ft²"), LocalizedText.english("candela per square foot"));

    public static final EUInformation CODE_B60 = new EUInformation(CEFACT_NAMESPACE_URI, 4339248, LocalizedText.english("lm/m²"), LocalizedText.english("lumen per square metre"));

    public static final EUInformation CODE_LUX = new EUInformation(CEFACT_NAMESPACE_URI, 5002584, LocalizedText.english("lx"), LocalizedText.english("lux"));

    public static final EUInformation CODE_KLX = new EUInformation(CEFACT_NAMESPACE_URI, 4934744, LocalizedText.english("klx"), LocalizedText.english("kilolux"));

    public static final EUInformation CODE_P25 = new EUInformation(CEFACT_NAMESPACE_URI, 5255733, LocalizedText.english("lm/ft²"), LocalizedText.english("lumen per square foot"));

    public static final EUInformation CODE_P26 = new EUInformation(CEFACT_NAMESPACE_URI, 5255734, LocalizedText.english("ph"), LocalizedText.english("phot"));

    public static final EUInformation CODE_P27 = new EUInformation(CEFACT_NAMESPACE_URI, 5255735, LocalizedText.english("ftc"), LocalizedText.english("footcandle"));

    public static final EUInformation CODE_B64 = new EUInformation(CEFACT_NAMESPACE_URI, 4339252, LocalizedText.english("lx·s"), LocalizedText.english("lux second"));

    public static final EUInformation CODE_B63 = new EUInformation(CEFACT_NAMESPACE_URI, 4339251, LocalizedText.english("lx·h"), LocalizedText.english("lux hour"));

    public static final EUInformation CODE_B61 = new EUInformation(CEFACT_NAMESPACE_URI, 4339249, LocalizedText.english("lm/W"), LocalizedText.english("lumen per watt"));

    public static final EUInformation CODE_D22 = new EUInformation(CEFACT_NAMESPACE_URI, 4469298, LocalizedText.english("m²/mol"), LocalizedText.english("square metre per mole"));

    public static final EUInformation CODE_D9 = new EUInformation(CEFACT_NAMESPACE_URI, 17465, LocalizedText.english("dyn/cm²"), LocalizedText.english("dyne per square centimetre"));

    public static final EUInformation CODE_A60 = new EUInformation(CEFACT_NAMESPACE_URI, 4273712, LocalizedText.english("erg/cm³"), LocalizedText.english("erg per cubic centimetre"));

    public static final EUInformation CODE_C32 = new EUInformation(CEFACT_NAMESPACE_URI, 4404018, LocalizedText.english("mW/m²"), LocalizedText.english("milliwatt per square metre"));

    public static final EUInformation CODE_D85 = new EUInformation(CEFACT_NAMESPACE_URI, 4470837, LocalizedText.english("µW/m²"), LocalizedText.english("microwatt per square metre"));

    public static final EUInformation CODE_C76 = new EUInformation(CEFACT_NAMESPACE_URI, 4405046, LocalizedText.english("pW/m²"), LocalizedText.english("picowatt per square metre"));

    public static final EUInformation CODE_A64 = new EUInformation(CEFACT_NAMESPACE_URI, 4273716, LocalizedText.english("erg/(s·cm²)"), LocalizedText.english("erg per second square centimetre"));

    public static final EUInformation CODE_C67 = new EUInformation(CEFACT_NAMESPACE_URI, 4404791, LocalizedText.english("Pa· s/m"), LocalizedText.english("pascal second per metre"));

    public static final EUInformation CODE_A50 = new EUInformation(CEFACT_NAMESPACE_URI, 4273456, LocalizedText.english("dyn·s/cm³"), LocalizedText.english("dyne second per cubic centimetre"));

    public static final EUInformation CODE_C66 = new EUInformation(CEFACT_NAMESPACE_URI, 4404790, LocalizedText.english("Pa·s/m³"), LocalizedText.english("pascal second per cubic metre"));

    public static final EUInformation CODE_A52 = new EUInformation(CEFACT_NAMESPACE_URI, 4273458, LocalizedText.english("dyn·s/cm⁵"), LocalizedText.english("dyne second per centimetre to the fifth power"));

    public static final EUInformation CODE_M32 = new EUInformation(CEFACT_NAMESPACE_URI, 5059378, LocalizedText.english("Pa·s/l"), LocalizedText.english("pascal second per litre"));

    public static final EUInformation CODE_C58 = new EUInformation(CEFACT_NAMESPACE_URI, 4404536, LocalizedText.english("N·s/m"), LocalizedText.english("newton second per metre"));

    public static final EUInformation CODE_A51 = new EUInformation(CEFACT_NAMESPACE_URI, 4273457, LocalizedText.english("dyn·s/cm"), LocalizedText.english("dyne second per centimetre"));

    public static final EUInformation CODE_P43 = new EUInformation(CEFACT_NAMESPACE_URI, 5256243, LocalizedText.english("B/m"), LocalizedText.english("bel per metre"));

    public static final EUInformation CODE_H51 = new EUInformation(CEFACT_NAMESPACE_URI, 4732209, LocalizedText.english("dB/km"), LocalizedText.english("decibel per kilometre"));

    public static final EUInformation CODE_H52 = new EUInformation(CEFACT_NAMESPACE_URI, 4732210, LocalizedText.english("dB/m"), LocalizedText.english("decibel per metre"));

    public static final EUInformation CODE_P42 = new EUInformation(CEFACT_NAMESPACE_URI, 5256242, LocalizedText.english("Pa²·s"), LocalizedText.english("pascal squared second"));

    public static final EUInformation CODE_P41 = new EUInformation(CEFACT_NAMESPACE_URI, 5256241, LocalizedText.english("dec"), LocalizedText.english("decade (logarithmic)"));

    public static final EUInformation CODE_C34 = new EUInformation(CEFACT_NAMESPACE_URI, 4404020, LocalizedText.english("mol"), LocalizedText.english("mole"));

    public static final EUInformation CODE_B45 = new EUInformation(CEFACT_NAMESPACE_URI, 4338741, LocalizedText.english("kmol"), LocalizedText.english("kilomole"));

    public static final EUInformation CODE_C18 = new EUInformation(CEFACT_NAMESPACE_URI, 4403512, LocalizedText.english("mmol"), LocalizedText.english("millimole"));

    public static final EUInformation CODE_FH = new EUInformation(CEFACT_NAMESPACE_URI, 17992, LocalizedText.english("µmol"), LocalizedText.english("micromole"));

    public static final EUInformation CODE_P44 = new EUInformation(CEFACT_NAMESPACE_URI, 5256244, LocalizedText.english("lbmol"), LocalizedText.english("pound mole"));

    public static final EUInformation CODE_C95 = new EUInformation(CEFACT_NAMESPACE_URI, 4405557, LocalizedText.english("mol⁻¹"), LocalizedText.english("reciprocal mole"));

    public static final EUInformation CODE_D74 = new EUInformation(CEFACT_NAMESPACE_URI, 4470580, LocalizedText.english("kg/mol"), LocalizedText.english("kilogram per mole"));

    public static final EUInformation CODE_A94 = new EUInformation(CEFACT_NAMESPACE_URI, 4274484, LocalizedText.english("g/mol"), LocalizedText.english("gram per mole"));

    public static final EUInformation CODE_A40 = new EUInformation(CEFACT_NAMESPACE_URI, 4273200, LocalizedText.english("m³/mol"), LocalizedText.english("cubic metre per mole"));

    public static final EUInformation CODE_A37 = new EUInformation(CEFACT_NAMESPACE_URI, 4272951, LocalizedText.english("dm³/mol"), LocalizedText.english("cubic decimetre per mole"));

    public static final EUInformation CODE_A36 = new EUInformation(CEFACT_NAMESPACE_URI, 4272950, LocalizedText.english("cm³/mol"), LocalizedText.english("cubic centimetre per mole"));

    public static final EUInformation CODE_B58 = new EUInformation(CEFACT_NAMESPACE_URI, 4339000, LocalizedText.english("l/mol"), LocalizedText.english("litre per mole"));

    public static final EUInformation CODE_B15 = new EUInformation(CEFACT_NAMESPACE_URI, 4337973, LocalizedText.english("J/mol"), LocalizedText.english("joule per mole"));

    public static final EUInformation CODE_B44 = new EUInformation(CEFACT_NAMESPACE_URI, 4338740, LocalizedText.english("kJ/mol"), LocalizedText.english("kilojoule per mole"));

    public static final EUInformation CODE_B16 = new EUInformation(CEFACT_NAMESPACE_URI, 4337974, LocalizedText.english("J/(mol·K)"), LocalizedText.english("joule per mole kelvin"));

    public static final EUInformation CODE_C86 = new EUInformation(CEFACT_NAMESPACE_URI, 4405302, LocalizedText.english("m⁻³"), LocalizedText.english("reciprocal cubic metre"));

    public static final EUInformation CODE_H50 = new EUInformation(CEFACT_NAMESPACE_URI, 4732208, LocalizedText.english("cm⁻³"), LocalizedText.english("reciprocal cubic centimetre"));

    public static final EUInformation CODE_L20 = new EUInformation(CEFACT_NAMESPACE_URI, 4993584, LocalizedText.english("1/mm³"), LocalizedText.english("reciprocal cubic millimetre"));

    public static final EUInformation CODE_K20 = new EUInformation(CEFACT_NAMESPACE_URI, 4928048, LocalizedText.english("1/ft³"), LocalizedText.english("reciprocal cubic foot"));

    public static final EUInformation CODE_K49 = new EUInformation(CEFACT_NAMESPACE_URI, 4928569, LocalizedText.english("1/in³"), LocalizedText.english("reciprocal cubic inch"));

    public static final EUInformation CODE_K63 = new EUInformation(CEFACT_NAMESPACE_URI, 4929075, LocalizedText.english("1/l"), LocalizedText.english("reciprocal litre"));

    public static final EUInformation CODE_M10 = new EUInformation(CEFACT_NAMESPACE_URI, 5058864, LocalizedText.english("1/yd³"), LocalizedText.english("reciprocal cubic yard"));

    public static final EUInformation CODE_C36 = new EUInformation(CEFACT_NAMESPACE_URI, 4404022, LocalizedText.english("mol/m³"), LocalizedText.english("mole per cubic metre"));

    public static final EUInformation CODE_C38 = new EUInformation(CEFACT_NAMESPACE_URI, 4404024, LocalizedText.english("mol/l"), LocalizedText.english("mole per litre"));

    public static final EUInformation CODE_C35 = new EUInformation(CEFACT_NAMESPACE_URI, 4404021, LocalizedText.english("mol/dm³"), LocalizedText.english("mole per cubic decimetre"));

    public static final EUInformation CODE_B46 = new EUInformation(CEFACT_NAMESPACE_URI, 4338742, LocalizedText.english("kmol/m³"), LocalizedText.english("kilomole per cubic metre"));

    public static final EUInformation CODE_E95 = new EUInformation(CEFACT_NAMESPACE_URI, 4536629, LocalizedText.english("mol/s"), LocalizedText.english("mole per second"));

    public static final EUInformation CODE_M33 = new EUInformation(CEFACT_NAMESPACE_URI, 5059379, LocalizedText.english("mmol/l"), LocalizedText.english("millimole per litre"));

    public static final EUInformation CODE_P51 = new EUInformation(CEFACT_NAMESPACE_URI, 5256497, LocalizedText.english("(mol/kg)/Pa"), LocalizedText.english("mol per kilogram pascal"));

    public static final EUInformation CODE_P52 = new EUInformation(CEFACT_NAMESPACE_URI, 5256498, LocalizedText.english("(mol/m³)/Pa"), LocalizedText.english("mol per cubic metre pascal"));

    public static final EUInformation CODE_K59 = new EUInformation(CEFACT_NAMESPACE_URI, 4928825, LocalizedText.english("(kmol/m³)/K"), LocalizedText.english("kilomole per cubic metre kelvin"));

    public static final EUInformation CODE_K60 = new EUInformation(CEFACT_NAMESPACE_URI, 4929072, LocalizedText.english("(kmol/m³)/bar"), LocalizedText.english("kilomole per cubic metre bar"));

    public static final EUInformation CODE_K93 = new EUInformation(CEFACT_NAMESPACE_URI, 4929843, LocalizedText.english("1/psi"), LocalizedText.english("reciprocal psi"));

    public static final EUInformation CODE_L24 = new EUInformation(CEFACT_NAMESPACE_URI, 4993588, LocalizedText.english("(mol/kg)/K"), LocalizedText.english("mole per kilogram kelvin"));

    public static final EUInformation CODE_L25 = new EUInformation(CEFACT_NAMESPACE_URI, 4993589, LocalizedText.english("(mol/kg)/bar"), LocalizedText.english("mole per kilogram bar"));

    public static final EUInformation CODE_L26 = new EUInformation(CEFACT_NAMESPACE_URI, 4993590, LocalizedText.english("(mol/l)/K"), LocalizedText.english("mole per litre kelvin"));

    public static final EUInformation CODE_L27 = new EUInformation(CEFACT_NAMESPACE_URI, 4993591, LocalizedText.english("(mol/l)/bar"), LocalizedText.english("mole per litre bar"));

    public static final EUInformation CODE_L28 = new EUInformation(CEFACT_NAMESPACE_URI, 4993592, LocalizedText.english("(mol/m³)/K"), LocalizedText.english("mole per cubic metre kelvin"));

    public static final EUInformation CODE_L29 = new EUInformation(CEFACT_NAMESPACE_URI, 4993593, LocalizedText.english("(mol/m³)/bar"), LocalizedText.english("mole per cubic metre bar"));

    public static final EUInformation CODE_C19 = new EUInformation(CEFACT_NAMESPACE_URI, 4403513, LocalizedText.english("mol/kg"), LocalizedText.english("mole per kilogram"));

    public static final EUInformation CODE_D93 = new EUInformation(CEFACT_NAMESPACE_URI, 4471091, LocalizedText.english("s/m³"), LocalizedText.english("second per cubic metre"));

    public static final EUInformation CODE_D87 = new EUInformation(CEFACT_NAMESPACE_URI, 4470839, LocalizedText.english("mmol/kg"), LocalizedText.english("millimole per kilogram"));

    public static final EUInformation CODE_H68 = new EUInformation(CEFACT_NAMESPACE_URI, 4732472, LocalizedText.english("mmol/g"), LocalizedText.english("millimole per gram"));

    public static final EUInformation CODE_P47 = new EUInformation(CEFACT_NAMESPACE_URI, 5256247, LocalizedText.english("kmol/kg"), LocalizedText.english("kilomole per kilogram"));

    public static final EUInformation CODE_P48 = new EUInformation(CEFACT_NAMESPACE_URI, 5256248, LocalizedText.english("lbmol/lb"), LocalizedText.english("pound mole per pound"));

    public static final EUInformation CODE_KAT = new EUInformation(CEFACT_NAMESPACE_URI, 4931924, LocalizedText.english("kat"), LocalizedText.english("katal"));

    public static final EUInformation CODE_E94 = new EUInformation(CEFACT_NAMESPACE_URI, 4536628, LocalizedText.english("kmol/s"), LocalizedText.english("kilomole per second"));

    public static final EUInformation CODE_P45 = new EUInformation(CEFACT_NAMESPACE_URI, 5256245, LocalizedText.english("lbmol/s"), LocalizedText.english("pound mole per second"));

    public static final EUInformation CODE_P46 = new EUInformation(CEFACT_NAMESPACE_URI, 5256246, LocalizedText.english("lbmol/h"), LocalizedText.english("pound mole per minute"));

    public static final EUInformation CODE_D43 = new EUInformation(CEFACT_NAMESPACE_URI, 4469811, LocalizedText.english("u"), LocalizedText.english("unified atomic mass unit"));

    public static final EUInformation CODE_A27 = new EUInformation(CEFACT_NAMESPACE_URI, 4272695, LocalizedText.english("C·m²/V"), LocalizedText.english("coulomb metre squared per volt"));

    public static final EUInformation CODE_A32 = new EUInformation(CEFACT_NAMESPACE_URI, 4272946, LocalizedText.english("C/mol"), LocalizedText.english("coulomb per mole"));

    public static final EUInformation CODE_D12 = new EUInformation(CEFACT_NAMESPACE_URI, 4469042, LocalizedText.english("S·m²/mol"), LocalizedText.english("siemens square metre per mole"));

    public static final EUInformation CODE_K58 = new EUInformation(CEFACT_NAMESPACE_URI, 4928824, LocalizedText.english("kmol/h"), LocalizedText.english("kilomole per hour"));

    public static final EUInformation CODE_K61 = new EUInformation(CEFACT_NAMESPACE_URI, 4929073, LocalizedText.english("kmol/min"), LocalizedText.english("kilomole per minute"));

    public static final EUInformation CODE_L23 = new EUInformation(CEFACT_NAMESPACE_URI, 4993587, LocalizedText.english("mol/h"), LocalizedText.english("mole per hour"));

    public static final EUInformation CODE_L30 = new EUInformation(CEFACT_NAMESPACE_URI, 4993840, LocalizedText.english("mol/min"), LocalizedText.english("mole per minute"));

    public static final EUInformation CODE_C82 = new EUInformation(CEFACT_NAMESPACE_URI, 4405298, LocalizedText.english("rad·m²/mol"), LocalizedText.english("radian square metre per mole"));

    public static final EUInformation CODE_C83 = new EUInformation(CEFACT_NAMESPACE_URI, 4405299, LocalizedText.english("rad·m²/kg"), LocalizedText.english("radian square metre per kilogram"));

    public static final EUInformation CODE_P49 = new EUInformation(CEFACT_NAMESPACE_URI, 5256249, LocalizedText.english("N·m²/A"), LocalizedText.english("newton square metre per ampere"));

    public static final EUInformation CODE_P50 = new EUInformation(CEFACT_NAMESPACE_URI, 5256496, LocalizedText.english("Wb·m"), LocalizedText.english("weber metre"));

    public static final EUInformation CODE_Q30 = new EUInformation(CEFACT_NAMESPACE_URI, 5321520, LocalizedText.english("pH"), LocalizedText.english("pH (potential of Hydrogen)"));

    public static final EUInformation CODE_B18 = new EUInformation(CEFACT_NAMESPACE_URI, 4337976, LocalizedText.english("J·s"), LocalizedText.english("joule second"));

    public static final EUInformation CODE_A10 = new EUInformation(CEFACT_NAMESPACE_URI, 4272432, LocalizedText.english("A·m²/(J·s)"), LocalizedText.english("ampere square metre per joule second"));

    public static final EUInformation CODE_CUR = new EUInformation(CEFACT_NAMESPACE_URI, 4412754, LocalizedText.english("Ci"), LocalizedText.english("curie"));

    public static final EUInformation CODE_MCU = new EUInformation(CEFACT_NAMESPACE_URI, 5063509, LocalizedText.english("mCi"), LocalizedText.english("millicurie"));

    public static final EUInformation CODE_M5 = new EUInformation(CEFACT_NAMESPACE_URI, 19765, LocalizedText.english("µCi"), LocalizedText.english("microcurie"));

    public static final EUInformation CODE_2R = new EUInformation(CEFACT_NAMESPACE_URI, 12882, LocalizedText.english("kCi"), LocalizedText.english("kilocurie"));

    public static final EUInformation CODE_BQL = new EUInformation(CEFACT_NAMESPACE_URI, 4346188, LocalizedText.english("Bq"), LocalizedText.english("becquerel"));

    public static final EUInformation CODE_GBQ = new EUInformation(CEFACT_NAMESPACE_URI, 4670033, LocalizedText.english("GBq"), LocalizedText.english("gigabecquerel"));

    public static final EUInformation CODE_2Q = new EUInformation(CEFACT_NAMESPACE_URI, 12881, LocalizedText.english("kBq"), LocalizedText.english("kilobecquerel"));

    public static final EUInformation CODE_4N = new EUInformation(CEFACT_NAMESPACE_URI, 13390, LocalizedText.english("MBq"), LocalizedText.english("megabecquerel"));

    public static final EUInformation CODE_H08 = new EUInformation(CEFACT_NAMESPACE_URI, 4730936, LocalizedText.english("µBq"), LocalizedText.english("microbecquerel"));

    public static final EUInformation CODE_A42 = new EUInformation(CEFACT_NAMESPACE_URI, 4273202, LocalizedText.english("Ci/kg"), LocalizedText.english("curie per kilogram"));

    public static final EUInformation CODE_A18 = new EUInformation(CEFACT_NAMESPACE_URI, 4272440, LocalizedText.english("Bq/kg"), LocalizedText.english("becquerel per kilogram"));

    public static final EUInformation CODE_B67 = new EUInformation(CEFACT_NAMESPACE_URI, 4339255, LocalizedText.english("MBq/kg"), LocalizedText.english("megabecquerel per kilogram"));

    public static final EUInformation CODE_B25 = new EUInformation(CEFACT_NAMESPACE_URI, 4338229, LocalizedText.english("kBq/kg"), LocalizedText.english("kilobecquerel per kilogram"));

    public static final EUInformation CODE_A19 = new EUInformation(CEFACT_NAMESPACE_URI, 4272441, LocalizedText.english("Bq/m³"), LocalizedText.english("becquerel per cubic metre"));

    public static final EUInformation CODE_A14 = new EUInformation(CEFACT_NAMESPACE_URI, 4272436, LocalizedText.english("b"), LocalizedText.english("barn"));

    public static final EUInformation CODE_D24 = new EUInformation(CEFACT_NAMESPACE_URI, 4469300, LocalizedText.english("m²/sr"), LocalizedText.english("square metre per steradian"));

    public static final EUInformation CODE_A17 = new EUInformation(CEFACT_NAMESPACE_URI, 4272439, LocalizedText.english("b/sr"), LocalizedText.english("barn per steradian"));

    public static final EUInformation CODE_D20 = new EUInformation(CEFACT_NAMESPACE_URI, 4469296, LocalizedText.english("m²/J"), LocalizedText.english("square metre per joule"));

    public static final EUInformation CODE_A15 = new EUInformation(CEFACT_NAMESPACE_URI, 4272437, LocalizedText.english("b/eV"), LocalizedText.english("barn per electronvolt"));

    public static final EUInformation CODE_D16 = new EUInformation(CEFACT_NAMESPACE_URI, 4469046, LocalizedText.english("cm²/erg"), LocalizedText.english("square centimetre per erg"));

    public static final EUInformation CODE_D25 = new EUInformation(CEFACT_NAMESPACE_URI, 4469301, LocalizedText.english("m²/(sr·J)"), LocalizedText.english("square metre per steradian joule"));

    public static final EUInformation CODE_A16 = new EUInformation(CEFACT_NAMESPACE_URI, 4272438, LocalizedText.english("b/(sr·eV)"), LocalizedText.english("barn per steradian electronvolt"));

    public static final EUInformation CODE_D17 = new EUInformation(CEFACT_NAMESPACE_URI, 4469047, LocalizedText.english("cm²/(sr·erg)"), LocalizedText.english("square centimetre per steradian erg"));

    public static final EUInformation CODE_B81 = new EUInformation(CEFACT_NAMESPACE_URI, 4339761, LocalizedText.english("m⁻²/s"), LocalizedText.english("reciprocal metre squared reciprocal second"));

    public static final EUInformation CODE_A65 = new EUInformation(CEFACT_NAMESPACE_URI, 4273717, LocalizedText.english("erg/(cm²·s)"), LocalizedText.english("erg per square centimetre second"));

    public static final EUInformation CODE_D21 = new EUInformation(CEFACT_NAMESPACE_URI, 4469297, LocalizedText.english("m²/kg"), LocalizedText.english("square metre per kilogram"));

    public static final EUInformation CODE_B12 = new EUInformation(CEFACT_NAMESPACE_URI, 4337970, LocalizedText.english("J/m"), LocalizedText.english("joule per metre"));

    public static final EUInformation CODE_A54 = new EUInformation(CEFACT_NAMESPACE_URI, 4273460, LocalizedText.english("eV/m"), LocalizedText.english("electronvolt per metre"));

    public static final EUInformation CODE_A58 = new EUInformation(CEFACT_NAMESPACE_URI, 4273464, LocalizedText.english("erg/cm"), LocalizedText.english("erg per centimetre"));

    public static final EUInformation CODE_D73 = new EUInformation(CEFACT_NAMESPACE_URI, 4470579, LocalizedText.english("J·m²"), LocalizedText.english("joule square metre"));

    public static final EUInformation CODE_A55 = new EUInformation(CEFACT_NAMESPACE_URI, 4273461, LocalizedText.english("eV·m²"), LocalizedText.english("electronvolt square metre"));

    public static final EUInformation CODE_A66 = new EUInformation(CEFACT_NAMESPACE_URI, 4273718, LocalizedText.english("erg·cm²"), LocalizedText.english("erg square centimetre"));

    public static final EUInformation CODE_B20 = new EUInformation(CEFACT_NAMESPACE_URI, 4338224, LocalizedText.english("J·m²/kg"), LocalizedText.english("joule square metre per kilogram"));

    public static final EUInformation CODE_A56 = new EUInformation(CEFACT_NAMESPACE_URI, 4273462, LocalizedText.english("eV·m²/kg"), LocalizedText.english("electronvolt square metre per kilogram"));

    public static final EUInformation CODE_A67 = new EUInformation(CEFACT_NAMESPACE_URI, 4273719, LocalizedText.english("erg·cm²/g"), LocalizedText.english("erg square centimetre per gram"));

    public static final EUInformation CODE_D26 = new EUInformation(CEFACT_NAMESPACE_URI, 4469302, LocalizedText.english("m²/(V·s)"), LocalizedText.english("square metre per volt second"));

    public static final EUInformation CODE_H58 = new EUInformation(CEFACT_NAMESPACE_URI, 4732216, LocalizedText.english("m/(V·s)"), LocalizedText.english("metre per volt second"));

    public static final EUInformation CODE_C87 = new EUInformation(CEFACT_NAMESPACE_URI, 4405303, LocalizedText.english("m⁻³/s"), LocalizedText.english("reciprocal cubic metre per second"));

    public static final EUInformation CODE_A95 = new EUInformation(CEFACT_NAMESPACE_URI, 4274485, LocalizedText.english("Gy"), LocalizedText.english("gray"));

    public static final EUInformation CODE_C13 = new EUInformation(CEFACT_NAMESPACE_URI, 4403507, LocalizedText.english("mGy"), LocalizedText.english("milligray"));

    public static final EUInformation CODE_C80 = new EUInformation(CEFACT_NAMESPACE_URI, 4405296, LocalizedText.english("rad"), LocalizedText.english("rad"));

    public static final EUInformation CODE_A61 = new EUInformation(CEFACT_NAMESPACE_URI, 4273713, LocalizedText.english("erg/g"), LocalizedText.english("erg per gram"));

    public static final EUInformation CODE_D13 = new EUInformation(CEFACT_NAMESPACE_URI, 4469043, LocalizedText.english("Sv"), LocalizedText.english("sievert"));

    public static final EUInformation CODE_C28 = new EUInformation(CEFACT_NAMESPACE_URI, 4403768, LocalizedText.english("mSv"), LocalizedText.english("millisievert"));

    public static final EUInformation CODE_D91 = new EUInformation(CEFACT_NAMESPACE_URI, 4471089, LocalizedText.english("rem"), LocalizedText.english("rem"));

    public static final EUInformation CODE_L31 = new EUInformation(CEFACT_NAMESPACE_URI, 4993841, LocalizedText.english("mrem"), LocalizedText.english("milliroentgen aequivalent men"));

    public static final EUInformation CODE_A96 = new EUInformation(CEFACT_NAMESPACE_URI, 4274486, LocalizedText.english("Gy/s"), LocalizedText.english("gray per second"));

    public static final EUInformation CODE_A62 = new EUInformation(CEFACT_NAMESPACE_URI, 4273714, LocalizedText.english("erg/g·s"), LocalizedText.english("erg per gram second"));

    public static final EUInformation CODE_CKG = new EUInformation(CEFACT_NAMESPACE_URI, 4410183, LocalizedText.english("C/kg"), LocalizedText.english("coulomb per kilogram"));

    public static final EUInformation CODE_C8 = new EUInformation(CEFACT_NAMESPACE_URI, 17208, LocalizedText.english("mC/kg"), LocalizedText.english("millicoulomb per kilogram"));

    public static final EUInformation CODE_2C = new EUInformation(CEFACT_NAMESPACE_URI, 12867, LocalizedText.english("R"), LocalizedText.english("roentgen"));

    public static final EUInformation CODE_2Y = new EUInformation(CEFACT_NAMESPACE_URI, 12889, LocalizedText.english("mR"), LocalizedText.english("milliroentgen"));

    public static final EUInformation CODE_J53 = new EUInformation(CEFACT_NAMESPACE_URI, 4863283, LocalizedText.english("C·m²/kg"), LocalizedText.english("coulomb square metre per kilogram"));

    public static final EUInformation CODE_KR = new EUInformation(CEFACT_NAMESPACE_URI, 19282, LocalizedText.english("kR"), LocalizedText.english("kiloroentgen"));

    public static final EUInformation CODE_A31 = new EUInformation(CEFACT_NAMESPACE_URI, 4272945, LocalizedText.english("C/(kg·s)"), LocalizedText.english("coulomb per kilogram second"));

    public static final EUInformation CODE_D6 = new EUInformation(CEFACT_NAMESPACE_URI, 17462, LocalizedText.english("R/s"), LocalizedText.english("roentgen per second"));

    public static final EUInformation CODE_P54 = new EUInformation(CEFACT_NAMESPACE_URI, 5256500, LocalizedText.english("mGy/s"), LocalizedText.english("milligray per second"));

    public static final EUInformation CODE_P55 = new EUInformation(CEFACT_NAMESPACE_URI, 5256501, LocalizedText.english("µGy/s"), LocalizedText.english("microgray per second"));

    public static final EUInformation CODE_P56 = new EUInformation(CEFACT_NAMESPACE_URI, 5256502, LocalizedText.english("nGy/s"), LocalizedText.english("nanogray per second"));

    public static final EUInformation CODE_P57 = new EUInformation(CEFACT_NAMESPACE_URI, 5256503, LocalizedText.english("Gy/min"), LocalizedText.english("gray per minute"));

    public static final EUInformation CODE_P58 = new EUInformation(CEFACT_NAMESPACE_URI, 5256504, LocalizedText.english("mGy/min"), LocalizedText.english("milligray per minute"));

    public static final EUInformation CODE_P59 = new EUInformation(CEFACT_NAMESPACE_URI, 5256505, LocalizedText.english("µGy/min"), LocalizedText.english("microgray per minute"));

    public static final EUInformation CODE_P60 = new EUInformation(CEFACT_NAMESPACE_URI, 5256752, LocalizedText.english("nGy/min"), LocalizedText.english("nanogray per minute"));

    public static final EUInformation CODE_P61 = new EUInformation(CEFACT_NAMESPACE_URI, 5256753, LocalizedText.english("Gy/h"), LocalizedText.english("gray per hour"));

    public static final EUInformation CODE_P62 = new EUInformation(CEFACT_NAMESPACE_URI, 5256754, LocalizedText.english("mGy/h"), LocalizedText.english("milligray per hour"));

    public static final EUInformation CODE_P63 = new EUInformation(CEFACT_NAMESPACE_URI, 5256755, LocalizedText.english("µGy/h"), LocalizedText.english("microgray per hour"));

    public static final EUInformation CODE_P64 = new EUInformation(CEFACT_NAMESPACE_URI, 5256756, LocalizedText.english("nGy/h"), LocalizedText.english("nanogray per hour"));

    public static final EUInformation CODE_P65 = new EUInformation(CEFACT_NAMESPACE_URI, 5256757, LocalizedText.english("Sv/s"), LocalizedText.english("sievert per second"));

    public static final EUInformation CODE_P66 = new EUInformation(CEFACT_NAMESPACE_URI, 5256758, LocalizedText.english("mSv/s"), LocalizedText.english("millisievert per second"));

    public static final EUInformation CODE_P67 = new EUInformation(CEFACT_NAMESPACE_URI, 5256759, LocalizedText.english("µSv/s"), LocalizedText.english("microsievert per second"));

    public static final EUInformation CODE_P68 = new EUInformation(CEFACT_NAMESPACE_URI, 5256760, LocalizedText.english("nSv/s"), LocalizedText.english("nanosievert per second"));

    public static final EUInformation CODE_P69 = new EUInformation(CEFACT_NAMESPACE_URI, 5256761, LocalizedText.english("rem/s"), LocalizedText.english("rem per second"));

    public static final EUInformation CODE_P70 = new EUInformation(CEFACT_NAMESPACE_URI, 5257008, LocalizedText.english("Sv/h"), LocalizedText.english("sievert per hour"));

    public static final EUInformation CODE_P71 = new EUInformation(CEFACT_NAMESPACE_URI, 5257009, LocalizedText.english("mSv/h"), LocalizedText.english("millisievert per hour"));

    public static final EUInformation CODE_P72 = new EUInformation(CEFACT_NAMESPACE_URI, 5257010, LocalizedText.english("µSv/h"), LocalizedText.english("microsievert per hour"));

    public static final EUInformation CODE_P73 = new EUInformation(CEFACT_NAMESPACE_URI, 5257011, LocalizedText.english("nSv/h"), LocalizedText.english("nanosievert per hour"));

    public static final EUInformation CODE_P74 = new EUInformation(CEFACT_NAMESPACE_URI, 5257012, LocalizedText.english("Sv/min"), LocalizedText.english("sievert per minute"));

    public static final EUInformation CODE_P75 = new EUInformation(CEFACT_NAMESPACE_URI, 5257013, LocalizedText.english("mSv/min"), LocalizedText.english("millisievert per minute"));

    public static final EUInformation CODE_P76 = new EUInformation(CEFACT_NAMESPACE_URI, 5257014, LocalizedText.english("µSv/min"), LocalizedText.english("microsievert per minute"));

    public static final EUInformation CODE_P77 = new EUInformation(CEFACT_NAMESPACE_URI, 5257015, LocalizedText.english("nSv/min"), LocalizedText.english("nanosievert per minute"));

    public static final EUInformation CODE_P78 = new EUInformation(CEFACT_NAMESPACE_URI, 5257016, LocalizedText.english("1/in²"), LocalizedText.english("reciprocal square inch"));

    public static final EUInformation CODE_P53 = new EUInformation(CEFACT_NAMESPACE_URI, 5256499, LocalizedText.english("unit pole"), LocalizedText.english("unit pole"));

    public static final EUInformation CODE_C85 = new EUInformation(CEFACT_NAMESPACE_URI, 4405301, LocalizedText.english("Å⁻¹"), LocalizedText.english("reciprocal angstrom"));

    public static final EUInformation CODE_D94 = new EUInformation(CEFACT_NAMESPACE_URI, 4471092, LocalizedText.english("s/(rad·m³)"), LocalizedText.english("second per cubic metre radian"));

    public static final EUInformation CODE_C90 = new EUInformation(CEFACT_NAMESPACE_URI, 4405552, LocalizedText.english("J⁻¹/m³"), LocalizedText.english("reciprocal joule per cubic metre"));

    public static final EUInformation CODE_C88 = new EUInformation(CEFACT_NAMESPACE_URI, 4405304, LocalizedText.english("eV⁻¹/m³"), LocalizedText.english("reciprocal electron volt per cubic metre"));

    public static final EUInformation CODE_A38 = new EUInformation(CEFACT_NAMESPACE_URI, 4272952, LocalizedText.english("m³/C"), LocalizedText.english("cubic metre per coulomb"));

    public static final EUInformation CODE_D48 = new EUInformation(CEFACT_NAMESPACE_URI, 4469816, LocalizedText.english("V/K"), LocalizedText.english("volt per kelvin"));

    public static final EUInformation CODE_D49 = new EUInformation(CEFACT_NAMESPACE_URI, 4469817, LocalizedText.english("mV/K"), LocalizedText.english("millivolt per kelvin"));

    public static final EUInformation CODE_A6 = new EUInformation(CEFACT_NAMESPACE_URI, 16694, LocalizedText.english("A/(m²·K²)"), LocalizedText.english("ampere per square metre kelvin squared"));

    public static final EUInformation CODE_33 = new EUInformation(CEFACT_NAMESPACE_URI, 13107, LocalizedText.english("kPa·m²/g"), LocalizedText.english("kilopascal square metre per gram"));

    public static final EUInformation CODE_P79 = new EUInformation(CEFACT_NAMESPACE_URI, 5257017, LocalizedText.english("Pa/(kg/m²)"), LocalizedText.english("pascal square metre per kilogram"));

    public static final EUInformation CODE_34 = new EUInformation(CEFACT_NAMESPACE_URI, 13108, LocalizedText.english("kPa/mm"), LocalizedText.english("kilopascal per millimetre"));

    public static final EUInformation CODE_H42 = new EUInformation(CEFACT_NAMESPACE_URI, 4731954, LocalizedText.english("Pa/m"), LocalizedText.english("pascal per metre"));

    public static final EUInformation CODE_H69 = new EUInformation(CEFACT_NAMESPACE_URI, 4732473, LocalizedText.english("pPa/km"), LocalizedText.english("picopascal per kilometre"));

    public static final EUInformation CODE_P80 = new EUInformation(CEFACT_NAMESPACE_URI, 5257264, LocalizedText.english("mPa/m"), LocalizedText.english("millipascal per metre"));

    public static final EUInformation CODE_P81 = new EUInformation(CEFACT_NAMESPACE_URI, 5257265, LocalizedText.english("kPa/m"), LocalizedText.english("kilopascal per metre"));

    public static final EUInformation CODE_P82 = new EUInformation(CEFACT_NAMESPACE_URI, 5257266, LocalizedText.english("hPa/m"), LocalizedText.english("hectopascal per metre"));

    public static final EUInformation CODE_P83 = new EUInformation(CEFACT_NAMESPACE_URI, 5257267, LocalizedText.english("Atm/m"), LocalizedText.english("standard atmosphere per metre"));

    public static final EUInformation CODE_P84 = new EUInformation(CEFACT_NAMESPACE_URI, 5257268, LocalizedText.english("at/m"), LocalizedText.english("technical atmosphere per metre"));

    public static final EUInformation CODE_P85 = new EUInformation(CEFACT_NAMESPACE_URI, 5257269, LocalizedText.english("Torr/m"), LocalizedText.english("torr per metre"));

    public static final EUInformation CODE_P86 = new EUInformation(CEFACT_NAMESPACE_URI, 5257270, LocalizedText.english("psi/in"), LocalizedText.english("psi per inch"));

    public static final EUInformation CODE_35 = new EUInformation(CEFACT_NAMESPACE_URI, 13109, LocalizedText.english("ml/(cm²·s)"), LocalizedText.english("millilitre per square centimetre second"));

    public static final EUInformation CODE_P87 = new EUInformation(CEFACT_NAMESPACE_URI, 5257271, LocalizedText.english("(m³/s)/m²"), LocalizedText.english("cubic metre per second square metre"));

    public static final EUInformation CODE_OPM = new EUInformation(CEFACT_NAMESPACE_URI, 5197901, LocalizedText.english("o/min"), LocalizedText.english("oscillations per minute"));

    public static final EUInformation CODE_KNM = new EUInformation(CEFACT_NAMESPACE_URI, 4935245, LocalizedText.english("KN/m2"), LocalizedText.english("kilonewton per square metre"));

    public static final EUInformation CODE_Q35 = new EUInformation(CEFACT_NAMESPACE_URI, 5321525, LocalizedText.english("MW/min"), LocalizedText.english("megawatts per minute"));

    public static final EUInformation CODE_38 = new EUInformation(CEFACT_NAMESPACE_URI, 13112, LocalizedText.english("oz/(ft²/cin)"), LocalizedText.english("ounce per square foot per 0,01inch"));

    public static final EUInformation CODE_59 = new EUInformation(CEFACT_NAMESPACE_URI, 13625, LocalizedText.english("ppm"), LocalizedText.english("part per million"));

    public static final EUInformation CODE_61 = new EUInformation(CEFACT_NAMESPACE_URI, 13873, LocalizedText.english("ppb"), LocalizedText.english("part per billion (US)"));

    public static final EUInformation CODE_66 = new EUInformation(CEFACT_NAMESPACE_URI, 13878, LocalizedText.english("Oe"), LocalizedText.english("oersted"));

    public static final EUInformation CODE_76 = new EUInformation(CEFACT_NAMESPACE_URI, 14134, LocalizedText.english("Gs"), LocalizedText.english("gauss"));

    public static final EUInformation CODE_78 = new EUInformation(CEFACT_NAMESPACE_URI, 14136, LocalizedText.english("kGs"), LocalizedText.english("kilogauss"));

    public static final EUInformation CODE_2G = new EUInformation(CEFACT_NAMESPACE_URI, 12871, LocalizedText.english("V"), LocalizedText.english("volt AC"));

    public static final EUInformation CODE_2H = new EUInformation(CEFACT_NAMESPACE_URI, 12872, LocalizedText.english("V"), LocalizedText.english("volt DC"));

    public static final EUInformation CODE_2P = new EUInformation(CEFACT_NAMESPACE_URI, 12880, LocalizedText.english("kbyte"), LocalizedText.english("kilobyte"));

    public static final EUInformation CODE_4L = new EUInformation(CEFACT_NAMESPACE_URI, 13388, LocalizedText.english("Mbyte"), LocalizedText.english("megabyte"));

    public static final EUInformation CODE_A43 = new EUInformation(CEFACT_NAMESPACE_URI, 4273203, LocalizedText.english("dwt"), LocalizedText.english("deadweight tonnage"));

    public static final EUInformation CODE_A47 = new EUInformation(CEFACT_NAMESPACE_URI, 4273207, LocalizedText.english("dtex (g/10km)"), LocalizedText.english("decitex"));

    public static final EUInformation CODE_A49 = new EUInformation(CEFACT_NAMESPACE_URI, 4273209, LocalizedText.english("den (g/9 km)"), LocalizedText.english("denier"));

    public static final EUInformation CODE_A99 = new EUInformation(CEFACT_NAMESPACE_URI, 4274489, LocalizedText.english("bit"), LocalizedText.english("bit"));

    public static final EUInformation CODE_AB = new EUInformation(CEFACT_NAMESPACE_URI, 16706, LocalizedText.english("pk"), LocalizedText.english("bulk pack"));

    public static final EUInformation CODE_AD = new EUInformation(CEFACT_NAMESPACE_URI, 16708, LocalizedText.english("byte"), LocalizedText.english("byte"));

    public static final EUInformation CODE_B1 = new EUInformation(CEFACT_NAMESPACE_URI, 16945, LocalizedText.english("barrel (US)/d"), LocalizedText.english("barrel (US) per day"));

    public static final EUInformation CODE_B10 = new EUInformation(CEFACT_NAMESPACE_URI, 4337968, LocalizedText.english("bit/s"), LocalizedText.english("bit per second"));

    public static final EUInformation CODE_B30 = new EUInformation(CEFACT_NAMESPACE_URI, 4338480, LocalizedText.english("Gibit"), LocalizedText.english("gibibit"));

    public static final EUInformation CODE_B65 = new EUInformation(CEFACT_NAMESPACE_URI, 4339253, LocalizedText.english("Mx"), LocalizedText.english("maxwell"));

    public static final EUInformation CODE_B68 = new EUInformation(CEFACT_NAMESPACE_URI, 4339256, LocalizedText.english("Gbit"), LocalizedText.english("gigabit"));

    public static final EUInformation CODE_B80 = new EUInformation(CEFACT_NAMESPACE_URI, 4339760, LocalizedText.english("Gbit/s"), LocalizedText.english("gigabit per second"));

    public static final EUInformation CODE_BFT = new EUInformation(CEFACT_NAMESPACE_URI, 4343380, LocalizedText.english("fbm"), LocalizedText.english("board foot"));

    public static final EUInformation CODE_BPM = new EUInformation(CEFACT_NAMESPACE_URI, 4345933, LocalizedText.english("BPM"), LocalizedText.english("beats per minute"));

    public static final EUInformation CODE_C21 = new EUInformation(CEFACT_NAMESPACE_URI, 4403761, LocalizedText.english("Kibit"), LocalizedText.english("kibibit"));

    public static final EUInformation CODE_C37 = new EUInformation(CEFACT_NAMESPACE_URI, 4404023, LocalizedText.english("kbit"), LocalizedText.english("kilobit"));

    public static final EUInformation CODE_C74 = new EUInformation(CEFACT_NAMESPACE_URI, 4405044, LocalizedText.english("kbit/s"), LocalizedText.english("kilobit per second"));

    public static final EUInformation CODE_C79 = new EUInformation(CEFACT_NAMESPACE_URI, 4405049, LocalizedText.english("kVAh"), LocalizedText.english("kilovolt ampere hour"));

    public static final EUInformation CODE_D03 = new EUInformation(CEFACT_NAMESPACE_URI, 4468787, LocalizedText.english("kW·h/h"), LocalizedText.english("kilowatt hour per hour"));

    public static final EUInformation CODE_D11 = new EUInformation(CEFACT_NAMESPACE_URI, 4469041, LocalizedText.english("Mibit"), LocalizedText.english("mebibit"));

    public static final EUInformation CODE_D34 = new EUInformation(CEFACT_NAMESPACE_URI, 4469556, LocalizedText.english("tex (g/km)"), LocalizedText.english("tex"));

    public static final EUInformation CODE_D36 = new EUInformation(CEFACT_NAMESPACE_URI, 4469558, LocalizedText.english("Mbit"), LocalizedText.english("megabit"));

    public static final EUInformation CODE_D78 = new EUInformation(CEFACT_NAMESPACE_URI, 4470584, LocalizedText.english("MJ/s"), LocalizedText.english("megajoule per second"));

    public static final EUInformation CODE_DZN = new EUInformation(CEFACT_NAMESPACE_URI, 4479566, LocalizedText.english("DOZ"), LocalizedText.english("dozen"));

    public static final EUInformation CODE_E07 = new EUInformation(CEFACT_NAMESPACE_URI, 4534327, LocalizedText.english("MW·h/h"), LocalizedText.english("megawatt hour per hour"));

    public static final EUInformation CODE_E08 = new EUInformation(CEFACT_NAMESPACE_URI, 4534328, LocalizedText.english("MW/Hz"), LocalizedText.english("megawatt per hertz"));

    public static final EUInformation CODE_E10 = new EUInformation(CEFACT_NAMESPACE_URI, 4534576, LocalizedText.english("deg da"), LocalizedText.english("degree day"));

    public static final EUInformation CODE_E16 = new EUInformation(CEFACT_NAMESPACE_URI, 4534582, LocalizedText.english("BtuIT/h"), LocalizedText.english("million Btu(IT) per hour"));

    public static final EUInformation CODE_E17 = new EUInformation(CEFACT_NAMESPACE_URI, 4534583, LocalizedText.english("ft³/s"), LocalizedText.english("cubic foot per second"));

    public static final EUInformation CODE_E20 = new EUInformation(CEFACT_NAMESPACE_URI, 4534832, LocalizedText.english("Mbit/s"), LocalizedText.english("megabit per second"));

    public static final EUInformation CODE_E31 = new EUInformation(CEFACT_NAMESPACE_URI, 4535089, LocalizedText.english("m²/l"), LocalizedText.english("square metre per litre"));

    public static final EUInformation CODE_E32 = new EUInformation(CEFACT_NAMESPACE_URI, 4535090, LocalizedText.english("l/h"), LocalizedText.english("litre per hour"));

    public static final EUInformation CODE_E34 = new EUInformation(CEFACT_NAMESPACE_URI, 4535092, LocalizedText.english("Gbyte"), LocalizedText.english("gigabyte"));

    public static final EUInformation CODE_E35 = new EUInformation(CEFACT_NAMESPACE_URI, 4535093, LocalizedText.english("Tbyte"), LocalizedText.english("terabyte"));

    public static final EUInformation CODE_E36 = new EUInformation(CEFACT_NAMESPACE_URI, 4535094, LocalizedText.english("Pbyte"), LocalizedText.english("petabyte"));

    public static final EUInformation CODE_E39 = new EUInformation(CEFACT_NAMESPACE_URI, 4535097, LocalizedText.english("dpi"), LocalizedText.english("dots per inch"));

    public static final EUInformation CODE_E40 = new EUInformation(CEFACT_NAMESPACE_URI, 4535344, LocalizedText.english("ppht"), LocalizedText.english("part per hundred thousand"));

    public static final EUInformation CODE_E44 = new EUInformation(CEFACT_NAMESPACE_URI, 4535348, LocalizedText.english("kgf·m/cm²"), LocalizedText.english("kilogram-force metre per square centimetre"));

    public static final EUInformation CODE_E46 = new EUInformation(CEFACT_NAMESPACE_URI, 4535350, LocalizedText.english("kW·h/m³"), LocalizedText.english("kilowatt hour per cubic metre"));

    public static final EUInformation CODE_E47 = new EUInformation(CEFACT_NAMESPACE_URI, 4535351, LocalizedText.english("kW·h/K"), LocalizedText.english("kilowatt hour per kelvin"));

    public static final EUInformation CODE_E58 = new EUInformation(CEFACT_NAMESPACE_URI, 4535608, LocalizedText.english("Ebit/s"), LocalizedText.english("exabit per second"));

    public static final EUInformation CODE_E59 = new EUInformation(CEFACT_NAMESPACE_URI, 4535609, LocalizedText.english("Eibyte"), LocalizedText.english("exbibyte"));

    public static final EUInformation CODE_E60 = new EUInformation(CEFACT_NAMESPACE_URI, 4535856, LocalizedText.english("Pibyte"), LocalizedText.english("pebibyte"));

    public static final EUInformation CODE_E61 = new EUInformation(CEFACT_NAMESPACE_URI, 4535857, LocalizedText.english("Tibyte"), LocalizedText.english("tebibyte"));

    public static final EUInformation CODE_E62 = new EUInformation(CEFACT_NAMESPACE_URI, 4535858, LocalizedText.english("Gibyte"), LocalizedText.english("gibibyte"));

    public static final EUInformation CODE_E63 = new EUInformation(CEFACT_NAMESPACE_URI, 4535859, LocalizedText.english("Mibyte"), LocalizedText.english("mebibyte"));

    public static final EUInformation CODE_E64 = new EUInformation(CEFACT_NAMESPACE_URI, 4535860, LocalizedText.english("Kibyte"), LocalizedText.english("kibibyte"));

    public static final EUInformation CODE_E65 = new EUInformation(CEFACT_NAMESPACE_URI, 4535861, LocalizedText.english("Eibit/m"), LocalizedText.english("exbibit per metre"));

    public static final EUInformation CODE_E66 = new EUInformation(CEFACT_NAMESPACE_URI, 4535862, LocalizedText.english("Eibit/m²"), LocalizedText.english("exbibit per square metre"));

    public static final EUInformation CODE_E67 = new EUInformation(CEFACT_NAMESPACE_URI, 4535863, LocalizedText.english("Eibit/m³"), LocalizedText.english("exbibit per cubic metre"));

    public static final EUInformation CODE_E68 = new EUInformation(CEFACT_NAMESPACE_URI, 4535864, LocalizedText.english("Gbyte/s"), LocalizedText.english("gigabyte per second"));

    public static final EUInformation CODE_E69 = new EUInformation(CEFACT_NAMESPACE_URI, 4535865, LocalizedText.english("Gibit/m"), LocalizedText.english("gibibit per metre"));

    public static final EUInformation CODE_E70 = new EUInformation(CEFACT_NAMESPACE_URI, 4536112, LocalizedText.english("Gibit/m²"), LocalizedText.english("gibibit per square metre"));

    public static final EUInformation CODE_E71 = new EUInformation(CEFACT_NAMESPACE_URI, 4536113, LocalizedText.english("Gibit/m³"), LocalizedText.english("gibibit per cubic metre"));

    public static final EUInformation CODE_E72 = new EUInformation(CEFACT_NAMESPACE_URI, 4536114, LocalizedText.english("Kibit/m"), LocalizedText.english("kibibit per metre"));

    public static final EUInformation CODE_E73 = new EUInformation(CEFACT_NAMESPACE_URI, 4536115, LocalizedText.english("Kibit/m²"), LocalizedText.english("kibibit per square metre"));

    public static final EUInformation CODE_E74 = new EUInformation(CEFACT_NAMESPACE_URI, 4536116, LocalizedText.english("Kibit/m³"), LocalizedText.english("kibibit per cubic metre"));

    public static final EUInformation CODE_E75 = new EUInformation(CEFACT_NAMESPACE_URI, 4536117, LocalizedText.english("Mibit/m"), LocalizedText.english("mebibit per metre"));

    public static final EUInformation CODE_E76 = new EUInformation(CEFACT_NAMESPACE_URI, 4536118, LocalizedText.english("Mibit/m²"), LocalizedText.english("mebibit per square metre"));

    public static final EUInformation CODE_E77 = new EUInformation(CEFACT_NAMESPACE_URI, 4536119, LocalizedText.english("Mibit/m³"), LocalizedText.english("mebibit per cubic metre"));

    public static final EUInformation CODE_E78 = new EUInformation(CEFACT_NAMESPACE_URI, 4536120, LocalizedText.english("Pbit"), LocalizedText.english("petabit"));

    public static final EUInformation CODE_E79 = new EUInformation(CEFACT_NAMESPACE_URI, 4536121, LocalizedText.english("Pbit/s"), LocalizedText.english("petabit per second"));

    public static final EUInformation CODE_E80 = new EUInformation(CEFACT_NAMESPACE_URI, 4536368, LocalizedText.english("Pibit/m"), LocalizedText.english("pebibit per metre"));

    public static final EUInformation CODE_E81 = new EUInformation(CEFACT_NAMESPACE_URI, 4536369, LocalizedText.english("Pibit/m²"), LocalizedText.english("pebibit per square metre"));

    public static final EUInformation CODE_E82 = new EUInformation(CEFACT_NAMESPACE_URI, 4536370, LocalizedText.english("Pibit/m³"), LocalizedText.english("pebibit per cubic metre"));

    public static final EUInformation CODE_E83 = new EUInformation(CEFACT_NAMESPACE_URI, 4536371, LocalizedText.english("Tbit"), LocalizedText.english("terabit"));

    public static final EUInformation CODE_E84 = new EUInformation(CEFACT_NAMESPACE_URI, 4536372, LocalizedText.english("Tbit/s"), LocalizedText.english("terabit per second"));

    public static final EUInformation CODE_E85 = new EUInformation(CEFACT_NAMESPACE_URI, 4536373, LocalizedText.english("Tibit/m"), LocalizedText.english("tebibit per metre"));

    public static final EUInformation CODE_E86 = new EUInformation(CEFACT_NAMESPACE_URI, 4536374, LocalizedText.english("Tibit/m³"), LocalizedText.english("tebibit per cubic metre"));

    public static final EUInformation CODE_E87 = new EUInformation(CEFACT_NAMESPACE_URI, 4536375, LocalizedText.english("Tibit/m²"), LocalizedText.english("tebibit per square metre"));

    public static final EUInformation CODE_E88 = new EUInformation(CEFACT_NAMESPACE_URI, 4536376, LocalizedText.english("bit/m"), LocalizedText.english("bit per metre"));

    public static final EUInformation CODE_E89 = new EUInformation(CEFACT_NAMESPACE_URI, 4536377, LocalizedText.english("bit/m²"), LocalizedText.english("bit per square metre"));

    public static final EUInformation CODE_E90 = new EUInformation(CEFACT_NAMESPACE_URI, 4536624, LocalizedText.english("cm⁻¹"), LocalizedText.english("reciprocal centimetre"));

    public static final EUInformation CODE_E91 = new EUInformation(CEFACT_NAMESPACE_URI, 4536625, LocalizedText.english("d⁻¹"), LocalizedText.english("reciprocal day"));

    public static final EUInformation CODE_F01 = new EUInformation(CEFACT_NAMESPACE_URI, 4599857, LocalizedText.english("bit/m³"), LocalizedText.english("bit per cubic metre"));

    public static final EUInformation CODE_FC = new EUInformation(CEFACT_NAMESPACE_URI, 17987, LocalizedText.english("kft³"), LocalizedText.english("thousand cubic foot"));

    public static final EUInformation CODE_FIT = new EUInformation(CEFACT_NAMESPACE_URI, 4606292, LocalizedText.english("FIT"), LocalizedText.english("failures in time"));

    public static final EUInformation CODE_GB = new EUInformation(CEFACT_NAMESPACE_URI, 18242, LocalizedText.english("gal (US)/d"), LocalizedText.english("gallon (US) per day"));

    public static final EUInformation CODE_GFI = new EUInformation(CEFACT_NAMESPACE_URI, 4671049, LocalizedText.english("gi F/S"), LocalizedText.english("gram of fissile isotope"));

    public static final EUInformation CODE_GIA = new EUInformation(CEFACT_NAMESPACE_URI, 4671809, LocalizedText.english("gi (US)"), LocalizedText.english("gill (US)"));

    public static final EUInformation CODE_GII = new EUInformation(CEFACT_NAMESPACE_URI, 4671817, LocalizedText.english("gi (UK)"), LocalizedText.english("gill (UK)"));

    public static final EUInformation CODE_GRO = new EUInformation(CEFACT_NAMESPACE_URI, 4674127, LocalizedText.english("gr"), LocalizedText.english("gross"));

    public static final EUInformation CODE_H25 = new EUInformation(CEFACT_NAMESPACE_URI, 4731445, LocalizedText.english("%/K"), LocalizedText.english("percent per kelvin"));

    public static final EUInformation CODE_H71 = new EUInformation(CEFACT_NAMESPACE_URI, 4732721, LocalizedText.english("%/mo"), LocalizedText.english("percent per month"));

    public static final EUInformation CODE_H72 = new EUInformation(CEFACT_NAMESPACE_URI, 4732722, LocalizedText.english("%/hbar"), LocalizedText.english("percent per hectobar"));

    public static final EUInformation CODE_H73 = new EUInformation(CEFACT_NAMESPACE_URI, 4732723, LocalizedText.english("%/daK"), LocalizedText.english("percent per decakelvin"));

    public static final EUInformation CODE_H77 = new EUInformation(CEFACT_NAMESPACE_URI, 4732727, LocalizedText.english("MW"), LocalizedText.english("module width"));

    public static final EUInformation CODE_H80 = new EUInformation(CEFACT_NAMESPACE_URI, 4732976, LocalizedText.english("U or RU"), LocalizedText.english("rack unit"));

    public static final EUInformation CODE_H82 = new EUInformation(CEFACT_NAMESPACE_URI, 4732978, LocalizedText.english("bp"), LocalizedText.english("big point"));

    public static final EUInformation CODE_H89 = new EUInformation(CEFACT_NAMESPACE_URI, 4732985, LocalizedText.english("%/Ω"), LocalizedText.english("percent per ohm"));

    public static final EUInformation CODE_H90 = new EUInformation(CEFACT_NAMESPACE_URI, 4733232, LocalizedText.english("%/°"), LocalizedText.english("percent per degree"));

    public static final EUInformation CODE_H91 = new EUInformation(CEFACT_NAMESPACE_URI, 4733233, LocalizedText.english("%/10000"), LocalizedText.english("percent per ten thousand"));

    public static final EUInformation CODE_H92 = new EUInformation(CEFACT_NAMESPACE_URI, 4733234, LocalizedText.english("%/100000"), LocalizedText.english("percent per one hundred thousand"));

    public static final EUInformation CODE_H93 = new EUInformation(CEFACT_NAMESPACE_URI, 4733235, LocalizedText.english("%/100"), LocalizedText.english("percent per hundred"));

    public static final EUInformation CODE_H94 = new EUInformation(CEFACT_NAMESPACE_URI, 4733236, LocalizedText.english("%/1000"), LocalizedText.english("percent per thousand"));

    public static final EUInformation CODE_H95 = new EUInformation(CEFACT_NAMESPACE_URI, 4733237, LocalizedText.english("%/V"), LocalizedText.english("percent per volt"));

    public static final EUInformation CODE_H96 = new EUInformation(CEFACT_NAMESPACE_URI, 4733238, LocalizedText.english("%/bar"), LocalizedText.english("percent per bar"));

    public static final EUInformation CODE_H98 = new EUInformation(CEFACT_NAMESPACE_URI, 4733240, LocalizedText.english("%/in"), LocalizedText.english("percent per inch"));

    public static final EUInformation CODE_H99 = new EUInformation(CEFACT_NAMESPACE_URI, 4733241, LocalizedText.english("%/m"), LocalizedText.english("percent per metre"));

    public static final EUInformation CODE_HMQ = new EUInformation(CEFACT_NAMESPACE_URI, 4738385, LocalizedText.english("Mm³"), LocalizedText.english("million cubic metre"));

    public static final EUInformation CODE_J10 = new EUInformation(CEFACT_NAMESPACE_URI, 4862256, LocalizedText.english("%/mm"), LocalizedText.english("percent per millimetre"));

    public static final EUInformation CODE_J12 = new EUInformation(CEFACT_NAMESPACE_URI, 4862258, LocalizedText.english("‰/psi"), LocalizedText.english("per mille per psi"));

    public static final EUInformation CODE_J13 = new EUInformation(CEFACT_NAMESPACE_URI, 4862259, LocalizedText.english("°API"), LocalizedText.english("degree API"));

    public static final EUInformation CODE_J14 = new EUInformation(CEFACT_NAMESPACE_URI, 4862260, LocalizedText.english("°Bé"), LocalizedText.english("degree Baume (origin scale)"));

    public static final EUInformation CODE_J15 = new EUInformation(CEFACT_NAMESPACE_URI, 4862261, LocalizedText.english("°Bé (US heavy)"), LocalizedText.english("degree Baume (US heavy)"));

    public static final EUInformation CODE_J16 = new EUInformation(CEFACT_NAMESPACE_URI, 4862262, LocalizedText.english("°Bé (US light)"), LocalizedText.english("degree Baume (US light)"));

    public static final EUInformation CODE_J17 = new EUInformation(CEFACT_NAMESPACE_URI, 4862263, LocalizedText.english("°Balling"), LocalizedText.english("degree Balling"));

    public static final EUInformation CODE_J18 = new EUInformation(CEFACT_NAMESPACE_URI, 4862264, LocalizedText.english("°Bx"), LocalizedText.english("degree Brix"));

    public static final EUInformation CODE_J27 = new EUInformation(CEFACT_NAMESPACE_URI, 4862519, LocalizedText.english("°Oechsle"), LocalizedText.english("degree Oechsle"));

    public static final EUInformation CODE_J31 = new EUInformation(CEFACT_NAMESPACE_URI, 4862769, LocalizedText.english("°Tw"), LocalizedText.english("degree Twaddell"));

    public static final EUInformation CODE_J38 = new EUInformation(CEFACT_NAMESPACE_URI, 4862776, LocalizedText.english("Bd"), LocalizedText.english("baud"));

    public static final EUInformation CODE_J54 = new EUInformation(CEFACT_NAMESPACE_URI, 4863284, LocalizedText.english("MBd"), LocalizedText.english("megabaud"));

    public static final EUInformation CODE_K3 = new EUInformation(CEFACT_NAMESPACE_URI, 19251, LocalizedText.english("kvar·h"), LocalizedText.english("kilovolt ampere reactive hour"));

    public static final EUInformation CODE_K50 = new EUInformation(CEFACT_NAMESPACE_URI, 4928816, LocalizedText.english("kBd"), LocalizedText.english("kilobaud"));

    public static final EUInformation CODE_KCC = new EUInformation(CEFACT_NAMESPACE_URI, 4932419, LocalizedText.english("kg C₅ H₁₄ClNO"), LocalizedText.english("kilogram of choline chloride"));

    public static final EUInformation CODE_KDW = new EUInformation(CEFACT_NAMESPACE_URI, 4932695, LocalizedText.english("kg/net eda"), LocalizedText.english("kilogram drained net weight"));

    public static final EUInformation CODE_KHY = new EUInformation(CEFACT_NAMESPACE_URI, 4933721, LocalizedText.english("kg H₂O₂"), LocalizedText.english("kilogram of hydrogen peroxide"));

    public static final EUInformation CODE_KMA = new EUInformation(CEFACT_NAMESPACE_URI, 4934977, LocalizedText.english("kg met.am."), LocalizedText.english("kilogram of methylamine"));

    public static final EUInformation CODE_KNI = new EUInformation(CEFACT_NAMESPACE_URI, 4935241, LocalizedText.english("kg N"), LocalizedText.english("kilogram of nitrogen"));

    public static final EUInformation CODE_KPH = new EUInformation(CEFACT_NAMESPACE_URI, 4935752, LocalizedText.english("kg KOH"), LocalizedText.english("kilogram of potassium hydroxide (caustic potash)"));

    public static final EUInformation CODE_KPO = new EUInformation(CEFACT_NAMESPACE_URI, 4935759, LocalizedText.english("kg K₂O"), LocalizedText.english("kilogram of potassium oxide"));

    public static final EUInformation CODE_KSD = new EUInformation(CEFACT_NAMESPACE_URI, 4936516, LocalizedText.english("kg 90 % sdt"), LocalizedText.english("kilogram of substance 90 % dry"));

    public static final EUInformation CODE_KSH = new EUInformation(CEFACT_NAMESPACE_URI, 4936520, LocalizedText.english("kg NaOH"), LocalizedText.english("kilogram of sodium hydroxide (caustic soda)"));

    public static final EUInformation CODE_KUR = new EUInformation(CEFACT_NAMESPACE_URI, 4937042, LocalizedText.english("kg U"), LocalizedText.english("kilogram of uranium"));

    public static final EUInformation CODE_KWY = new EUInformation(CEFACT_NAMESPACE_URI, 4937561, LocalizedText.english("kW/year"), LocalizedText.english("kilowatt year"));

    public static final EUInformation CODE_KWO = new EUInformation(CEFACT_NAMESPACE_URI, 4937551, LocalizedText.english("kg WO₃"), LocalizedText.english("kilogram of tungsten trioxide"));

    public static final EUInformation CODE_M19 = new EUInformation(CEFACT_NAMESPACE_URI, 5058873, LocalizedText.english("Bft"), LocalizedText.english("Beaufort"));

    public static final EUInformation CODE_M25 = new EUInformation(CEFACT_NAMESPACE_URI, 5059125, LocalizedText.english("%/°C"), LocalizedText.english("percent per degree Celsius"));

    public static final EUInformation CODE_M36 = new EUInformation(CEFACT_NAMESPACE_URI, 5059382, LocalizedText.english("mo (30 days)"), LocalizedText.english("30-day month"));

    public static final EUInformation CODE_M37 = new EUInformation(CEFACT_NAMESPACE_URI, 5059383, LocalizedText.english("y (360 days)"), LocalizedText.english("actual/360"));

    public static final EUInformation CODE_M9 = new EUInformation(CEFACT_NAMESPACE_URI, 19769, LocalizedText.english("MBTU/kft³"), LocalizedText.english("million Btu per 1000 cubic foot"));

    public static final EUInformation CODE_MAH = new EUInformation(CEFACT_NAMESPACE_URI, 5062984, LocalizedText.english("Mvar·h"), LocalizedText.english("megavolt ampere reactive hour"));

    public static final EUInformation CODE_NIL = new EUInformation(CEFACT_NAMESPACE_URI, 5130572, LocalizedText.english("()"), LocalizedText.english("nil"));

    public static final EUInformation CODE_NX = new EUInformation(CEFACT_NAMESPACE_URI, 20056, LocalizedText.english("‰"), LocalizedText.english("part per thousand"));

    public static final EUInformation CODE_P1 = new EUInformation(CEFACT_NAMESPACE_URI, 20529, LocalizedText.english("% or pct"), LocalizedText.english("percent"));

    public static final EUInformation CODE_P88 = new EUInformation(CEFACT_NAMESPACE_URI, 5257272, LocalizedText.english("rhe"), LocalizedText.english("rhe"));

    public static final EUInformation CODE_P89 = new EUInformation(CEFACT_NAMESPACE_URI, 5257273, LocalizedText.english("lbf·ft/in"), LocalizedText.english("pound-force foot per inch"));

    public static final EUInformation CODE_P90 = new EUInformation(CEFACT_NAMESPACE_URI, 5257520, LocalizedText.english("lbf·in/in"), LocalizedText.english("pound-force inch per inch"));

    public static final EUInformation CODE_P91 = new EUInformation(CEFACT_NAMESPACE_URI, 5257521, LocalizedText.english("perm (0 ºC)"), LocalizedText.english("perm (0 ºC)"));

    public static final EUInformation CODE_P92 = new EUInformation(CEFACT_NAMESPACE_URI, 5257522, LocalizedText.english("perm (23 ºC)"), LocalizedText.english("perm (23 ºC)"));

    public static final EUInformation CODE_P93 = new EUInformation(CEFACT_NAMESPACE_URI, 5257523, LocalizedText.english("byte/s"), LocalizedText.english("byte per second"));

    public static final EUInformation CODE_P94 = new EUInformation(CEFACT_NAMESPACE_URI, 5257524, LocalizedText.english("kbyte/s"), LocalizedText.english("kilobyte per second"));

    public static final EUInformation CODE_P95 = new EUInformation(CEFACT_NAMESPACE_URI, 5257525, LocalizedText.english("Mbyte/s"), LocalizedText.english("megabyte per second"));

    public static final EUInformation CODE_P96 = new EUInformation(CEFACT_NAMESPACE_URI, 5257526, LocalizedText.english("1/V"), LocalizedText.english("reciprocal volt"));

    public static final EUInformation CODE_P97 = new EUInformation(CEFACT_NAMESPACE_URI, 5257527, LocalizedText.english("1/rad"), LocalizedText.english("reciprocal radian"));

    public static final EUInformation CODE_P98 = new EUInformation(CEFACT_NAMESPACE_URI, 5257528, LocalizedText.english("PaΣνB"), LocalizedText.english("pascal to the power sum of stoichiometric numbers"));

    public static final EUInformation CODE_P99 = new EUInformation(CEFACT_NAMESPACE_URI, 5257529, LocalizedText.english("(mol/m³)∑νB"), LocalizedText.english("mole per cubiv metre to the power sum of stoichiometric numbers"));

    public static final EUInformation CODE_PLA = new EUInformation(CEFACT_NAMESPACE_URI, 5262401, LocalizedText.english("°P"), LocalizedText.english("degree Plato"));

    public static final EUInformation CODE_PQ = new EUInformation(CEFACT_NAMESPACE_URI, 20561, LocalizedText.english("ppi"), LocalizedText.english("page per inch"));

    public static final EUInformation CODE_PTN = new EUInformation(CEFACT_NAMESPACE_URI, 5264462, LocalizedText.english("PTN"), LocalizedText.english("portion"));

    public static final EUInformation CODE_Q10 = new EUInformation(CEFACT_NAMESPACE_URI, 5321008, LocalizedText.english("J/T"), LocalizedText.english("joule per tesla"));

    public static final EUInformation CODE_Q11 = new EUInformation(CEFACT_NAMESPACE_URI, 5321009, LocalizedText.english("E"), LocalizedText.english("erlang"));

    public static final EUInformation CODE_Q12 = new EUInformation(CEFACT_NAMESPACE_URI, 5321010, LocalizedText.english("o"), LocalizedText.english("octet"));

    public static final EUInformation CODE_Q13 = new EUInformation(CEFACT_NAMESPACE_URI, 5321011, LocalizedText.english("o/s"), LocalizedText.english("octet per second"));

    public static final EUInformation CODE_Q14 = new EUInformation(CEFACT_NAMESPACE_URI, 5321012, LocalizedText.english("Sh"), LocalizedText.english("shannon"));

    public static final EUInformation CODE_Q15 = new EUInformation(CEFACT_NAMESPACE_URI, 5321013, LocalizedText.english("Hart"), LocalizedText.english("hartley"));

    public static final EUInformation CODE_Q16 = new EUInformation(CEFACT_NAMESPACE_URI, 5321014, LocalizedText.english("nat"), LocalizedText.english("natural unit of information"));

    public static final EUInformation CODE_Q17 = new EUInformation(CEFACT_NAMESPACE_URI, 5321015, LocalizedText.english("Sh/s"), LocalizedText.english("shannon per second"));

    public static final EUInformation CODE_Q18 = new EUInformation(CEFACT_NAMESPACE_URI, 5321016, LocalizedText.english("Hart/s"), LocalizedText.english("hartley per second"));

    public static final EUInformation CODE_Q19 = new EUInformation(CEFACT_NAMESPACE_URI, 5321017, LocalizedText.english("nat/s"), LocalizedText.english("natural unit of information per second"));

    public static final EUInformation CODE_Q20 = new EUInformation(CEFACT_NAMESPACE_URI, 5321264, LocalizedText.english("s/kg"), LocalizedText.english("second per kilogramm"));

    public static final EUInformation CODE_Q21 = new EUInformation(CEFACT_NAMESPACE_URI, 5321265, LocalizedText.english("W·m²"), LocalizedText.english("watt square metre"));

    public static final EUInformation CODE_Q22 = new EUInformation(CEFACT_NAMESPACE_URI, 5321266, LocalizedText.english("1/(Hz·rad·m³)"), LocalizedText.english("second per radian cubic metre"));

    public static final EUInformation CODE_Q23 = new EUInformation(CEFACT_NAMESPACE_URI, 5321267, LocalizedText.english("1/Wb"), LocalizedText.english("weber to the power minus one"));

    public static final EUInformation CODE_Q24 = new EUInformation(CEFACT_NAMESPACE_URI, 5321268, LocalizedText.english("1/in"), LocalizedText.english("reciprocal inch"));

    public static final EUInformation CODE_Q25 = new EUInformation(CEFACT_NAMESPACE_URI, 5321269, LocalizedText.english("dpt"), LocalizedText.english("dioptre"));

    public static final EUInformation CODE_Q26 = new EUInformation(CEFACT_NAMESPACE_URI, 5321270, LocalizedText.english("1/1"), LocalizedText.english("one per one"));

    public static final EUInformation CODE_Q27 = new EUInformation(CEFACT_NAMESPACE_URI, 5321271, LocalizedText.english("N·m/m²"), LocalizedText.english("newton metre per metre"));

    public static final EUInformation CODE_Q28 = new EUInformation(CEFACT_NAMESPACE_URI, 5321272, LocalizedText.english("kg/(m²·Pa·s)"), LocalizedText.english("kilogram per square metre pascal second"));

    public static final EUInformation CODE_Q36 = new EUInformation(CEFACT_NAMESPACE_URI, 5321526, LocalizedText.english("m2/m3"), LocalizedText.english("square metre per cubic metre"));

    public static final EUInformation CODE_QR = new EUInformation(CEFACT_NAMESPACE_URI, 20818, LocalizedText.english("qr"), LocalizedText.english("quire"));

    public static final EUInformation CODE_QTR = new EUInformation(CEFACT_NAMESPACE_URI, 5330002, LocalizedText.english("Qr (UK)"), LocalizedText.english("quarter (UK)"));

    public static final EUInformation CODE_TAN = new EUInformation(CEFACT_NAMESPACE_URI, 5521742, LocalizedText.english("TAN"), LocalizedText.english("total acid number"));

    public static final EUInformation CODE_TKM = new EUInformation(CEFACT_NAMESPACE_URI, 5524301, LocalizedText.english("t·km"), LocalizedText.english("tonne kilometre"));

    public static final EUInformation CODE_TPI = new EUInformation(CEFACT_NAMESPACE_URI, 5525577, LocalizedText.english("TPI"), LocalizedText.english("teeth per inch"));

    public static final EUInformation CODE_TQD = new EUInformation(CEFACT_NAMESPACE_URI, 5525828, LocalizedText.english("km³/d"), LocalizedText.english("thousand cubic metre per day"));

    public static final EUInformation CODE_VA = new EUInformation(CEFACT_NAMESPACE_URI, 22081, LocalizedText.english("V·A / kg"), LocalizedText.english("volt - ampere per kilogram"));

    public static final EUInformation CODE_WA = new EUInformation(CEFACT_NAMESPACE_URI, 22337, LocalizedText.english("W/kg"), LocalizedText.english("watt per kilogram"));

    public static final EUInformation CODE_WSD = new EUInformation(CEFACT_NAMESPACE_URI, 5722948, LocalizedText.english("std"), LocalizedText.english("standard"));

    public static final EUInformation CODE_MRW = new EUInformation(CEFACT_NAMESPACE_URI, 5067351, LocalizedText.english("m·wk"), LocalizedText.english("Metre Week"));

    public static final EUInformation CODE_MKW = new EUInformation(CEFACT_NAMESPACE_URI, 5065559, LocalizedText.english("m²· wk"), LocalizedText.english("Square Metre Week"));

    public static final EUInformation CODE_MQW = new EUInformation(CEFACT_NAMESPACE_URI, 5067095, LocalizedText.english("m³·wk"), LocalizedText.english("Cubic Metre Week"));

    public static final EUInformation CODE_HWE = new EUInformation(CEFACT_NAMESPACE_URI, 4740933, LocalizedText.english("piece·k"), LocalizedText.english("Piece Week"));

    public static final EUInformation CODE_MRD = new EUInformation(CEFACT_NAMESPACE_URI, 5067332, LocalizedText.english("m·day"), LocalizedText.english("Metre Day"));

    public static final EUInformation CODE_MKD = new EUInformation(CEFACT_NAMESPACE_URI, 5065540, LocalizedText.english("m²·d"), LocalizedText.english("Square Metre Day"));

    public static final EUInformation CODE_MQD = new EUInformation(CEFACT_NAMESPACE_URI, 5067076, LocalizedText.english("m³·d"), LocalizedText.english("Cubic Metre Day"));

    public static final EUInformation CODE_HAD = new EUInformation(CEFACT_NAMESPACE_URI, 4735300, LocalizedText.english("piece·d"), LocalizedText.english("Piece Day"));

    public static final EUInformation CODE_MRM = new EUInformation(CEFACT_NAMESPACE_URI, 5067341, LocalizedText.english("m·mo"), LocalizedText.english("Metre Month"));

    public static final EUInformation CODE_MKM = new EUInformation(CEFACT_NAMESPACE_URI, 5065549, LocalizedText.english("m²·mo"), LocalizedText.english("Square Metre Month"));

    public static final EUInformation CODE_MQM = new EUInformation(CEFACT_NAMESPACE_URI, 5067085, LocalizedText.english("m³·mo"), LocalizedText.english("Cubic Metre Month"));

    public static final EUInformation CODE_HMO = new EUInformation(CEFACT_NAMESPACE_URI, 4738383, LocalizedText.english("piece·mo"), LocalizedText.english("Piece Month"));

    public static final EUInformation CODE_DBW = new EUInformation(CEFACT_NAMESPACE_URI, 4473431, LocalizedText.english("dBW"), LocalizedText.english("Decibel watt"));

    public static final EUInformation CODE_DBM = new EUInformation(CEFACT_NAMESPACE_URI, 4473421, LocalizedText.english("dBm"), LocalizedText.english("Decibel-milliwatts"));

    public static final EUInformation CODE_FNU = new EUInformation(CEFACT_NAMESPACE_URI, 4607573, LocalizedText.english("FNU"), LocalizedText.english("Formazin nephelometric unit"));

    public static final EUInformation CODE_NTU = new EUInformation(CEFACT_NAMESPACE_URI, 5133397, LocalizedText.english("NTU"), LocalizedText.english("Nephelometric turbidity unit"));

    static {
        BY_UNIT_ID.put(4405297, CODE_C81);
        BY_UNIT_ID.put(4403765, CODE_C25);
        BY_UNIT_ID.put(4340023, CODE_B97);
        BY_UNIT_ID.put(17476, CODE_DD);
        BY_UNIT_ID.put(4470321, CODE_D61);
        BY_UNIT_ID.put(4470322, CODE_D62);
        BY_UNIT_ID.put(4274481, CODE_A91);
        BY_UNIT_ID.put(5059635, CODE_M43);
        BY_UNIT_ID.put(5059636, CODE_M44);
        BY_UNIT_ID.put(4469303, CODE_D27);
        BY_UNIT_ID.put(4732215, CODE_H57);
        BY_UNIT_ID.put(5067858, CODE_MTR);
        BY_UNIT_ID.put(4536630, CODE_E96);
        BY_UNIT_ID.put(4731447, CODE_H27);
        BY_UNIT_ID.put(5059893, CODE_M55);
        BY_UNIT_ID.put(4476244, CODE_DMT);
        BY_UNIT_ID.put(4410708, CODE_CMT);
        BY_UNIT_ID.put(13384, CODE_4H);
        BY_UNIT_ID.put(5066068, CODE_MMT);
        BY_UNIT_ID.put(4738388, CODE_HMT);
        BY_UNIT_ID.put(4934996, CODE_KMT);
        BY_UNIT_ID.put(4404277, CODE_C45);
        BY_UNIT_ID.put(4404530, CODE_C52);
        BY_UNIT_ID.put(4273969, CODE_A71);
        BY_UNIT_ID.put(4273205, CODE_A45);
        BY_UNIT_ID.put(5131593, CODE_NMI);
        BY_UNIT_ID.put(4272433, CODE_A11);
        BY_UNIT_ID.put(4272434, CODE_A12);
        BY_UNIT_ID.put(4404787, CODE_C63);
        BY_UNIT_ID.put(4601138, CODE_F52);
        BY_UNIT_ID.put(4601136, CODE_F50);
        BY_UNIT_ID.put(4601137, CODE_F51);
        BY_UNIT_ID.put(4665398, CODE_G06);
        BY_UNIT_ID.put(4732980, CODE_H84);
        BY_UNIT_ID.put(4665396, CODE_G04);
        BY_UNIT_ID.put(4665397, CODE_G05);
        BY_UNIT_ID.put(4732729, CODE_H79);
        BY_UNIT_ID.put(16715, CODE_AK);
        BY_UNIT_ID.put(22577, CODE_X1);
        BY_UNIT_ID.put(4804168, CODE_INH);
        BY_UNIT_ID.put(19767, CODE_M7);
        BY_UNIT_ID.put(4607828, CODE_FOT);
        BY_UNIT_ID.put(5853764, CODE_YRD);
        BY_UNIT_ID.put(5459273, CODE_SMI);
        BY_UNIT_ID.put(14135, CODE_77);
        BY_UNIT_ID.put(4338999, CODE_B57);
        BY_UNIT_ID.put(4600889, CODE_F49);
        BY_UNIT_ID.put(5062989, CODE_MAM);
        BY_UNIT_ID.put(4927795, CODE_K13);
        BY_UNIT_ID.put(4927799, CODE_K17);
        BY_UNIT_ID.put(4928565, CODE_K45);
        BY_UNIT_ID.put(4928566, CODE_K46);
        BY_UNIT_ID.put(4995384, CODE_L98);
        BY_UNIT_ID.put(4995385, CODE_L99);
        BY_UNIT_ID.put(5059641, CODE_M49);
        BY_UNIT_ID.put(5059888, CODE_M50);
        BY_UNIT_ID.put(5059889, CODE_M51);
        BY_UNIT_ID.put(5059890, CODE_M52);
        BY_UNIT_ID.put(5059891, CODE_M53);
        BY_UNIT_ID.put(5067851, CODE_MTK);
        BY_UNIT_ID.put(4934987, CODE_KMK);
        BY_UNIT_ID.put(4731696, CODE_H30);
        BY_UNIT_ID.put(4732217, CODE_H59);
        BY_UNIT_ID.put(4473153, CODE_DAA);
        BY_UNIT_ID.put(4410699, CODE_CMK);
        BY_UNIT_ID.put(4476235, CODE_DMK);
        BY_UNIT_ID.put(4731190, CODE_H16);
        BY_UNIT_ID.put(4731192, CODE_H18);
        BY_UNIT_ID.put(5066059, CODE_MMK);
        BY_UNIT_ID.put(4280901, CODE_ARE);
        BY_UNIT_ID.put(4735314, CODE_HAR);
        BY_UNIT_ID.put(4804171, CODE_INK);
        BY_UNIT_ID.put(4609099, CODE_FTK);
        BY_UNIT_ID.put(5850187, CODE_YDK);
        BY_UNIT_ID.put(5065035, CODE_MIK);
        BY_UNIT_ID.put(5059640, CODE_M48);
        BY_UNIT_ID.put(4277074, CODE_ACR);
        BY_UNIT_ID.put(5059639, CODE_M47);
        BY_UNIT_ID.put(5067857, CODE_MTQ);
        BY_UNIT_ID.put(5062988, CODE_MAL);
        BY_UNIT_ID.put(5002322, CODE_LTR);
        BY_UNIT_ID.put(5066065, CODE_MMQ);
        BY_UNIT_ID.put(4410705, CODE_CMQ);
        BY_UNIT_ID.put(4476241, CODE_DMQ);
        BY_UNIT_ID.put(5065812, CODE_MLT);
        BY_UNIT_ID.put(4738132, CODE_HLT);
        BY_UNIT_ID.put(4410452, CODE_CLT);
        BY_UNIT_ID.put(4476225, CODE_DMA);
        BY_UNIT_ID.put(4731193, CODE_H19);
        BY_UNIT_ID.put(4731440, CODE_H20);
        BY_UNIT_ID.put(5060401, CODE_M71);
        BY_UNIT_ID.put(4475988, CODE_DLT);
        BY_UNIT_ID.put(13383, CODE_4G);
        BY_UNIT_ID.put(19254, CODE_K6);
        BY_UNIT_ID.put(4273204, CODE_A44);
        BY_UNIT_ID.put(4667700, CODE_G94);
        BY_UNIT_ID.put(4667701, CODE_G95);
        BY_UNIT_ID.put(4667702, CODE_G96);
        BY_UNIT_ID.put(4667703, CODE_G97);
        BY_UNIT_ID.put(4804177, CODE_INQ);
        BY_UNIT_ID.put(4609105, CODE_FTQ);
        BY_UNIT_ID.put(5850193, CODE_YDQ);
        BY_UNIT_ID.put(4672585, CODE_GLI);
        BY_UNIT_ID.put(4672588, CODE_GLL);
        BY_UNIT_ID.put(20564, CODE_PT);
        BY_UNIT_ID.put(5264457, CODE_PTI);
        BY_UNIT_ID.put(5329993, CODE_QTI);
        BY_UNIT_ID.put(5264460, CODE_PTL);
        BY_UNIT_ID.put(5329996, CODE_QTL);
        BY_UNIT_ID.put(5264452, CODE_PTD);
        BY_UNIT_ID.put(5200457, CODE_OZI);
        BY_UNIT_ID.put(20820, CODE_QT);
        BY_UNIT_ID.put(4863287, CODE_J57);
        BY_UNIT_ID.put(4928049, CODE_K21);
        BY_UNIT_ID.put(4928051, CODE_K23);
        BY_UNIT_ID.put(4994099, CODE_L43);
        BY_UNIT_ID.put(4995124, CODE_L84);
        BY_UNIT_ID.put(4995126, CODE_L86);
        BY_UNIT_ID.put(5058865, CODE_M11);
        BY_UNIT_ID.put(5058868, CODE_M14);
        BY_UNIT_ID.put(5200449, CODE_OZA);
        BY_UNIT_ID.put(4347209, CODE_BUI);
        BY_UNIT_ID.put(4347201, CODE_BUA);
        BY_UNIT_ID.put(4344908, CODE_BLL);
        BY_UNIT_ID.put(4344900, CODE_BLD);
        BY_UNIT_ID.put(4672580, CODE_GLD);
        BY_UNIT_ID.put(5329988, CODE_QTD);
        BY_UNIT_ID.put(4665910, CODE_G26);
        BY_UNIT_ID.put(4665905, CODE_G21);
        BY_UNIT_ID.put(4665908, CODE_G24);
        BY_UNIT_ID.put(4665909, CODE_G25);
        BY_UNIT_ID.put(4665907, CODE_G23);
        BY_UNIT_ID.put(5060151, CODE_M67);
        BY_UNIT_ID.put(5060152, CODE_M68);
        BY_UNIT_ID.put(5060153, CODE_M69);
        BY_UNIT_ID.put(5060400, CODE_M70);
        BY_UNIT_ID.put(4665911, CODE_G27);
        BY_UNIT_ID.put(4665913, CODE_G29);
        BY_UNIT_ID.put(4665912, CODE_G28);
        BY_UNIT_ID.put(4666160, CODE_G30);
        BY_UNIT_ID.put(4862774, CODE_J36);
        BY_UNIT_ID.put(4864055, CODE_J87);
        BY_UNIT_ID.put(4864305, CODE_J91);
        BY_UNIT_ID.put(4929074, CODE_K62);
        BY_UNIT_ID.put(4993337, CODE_L19);
        BY_UNIT_ID.put(4993585, CODE_L21);
        BY_UNIT_ID.put(5457219, CODE_SEC);
        BY_UNIT_ID.put(5065038, CODE_MIN);
        BY_UNIT_ID.put(4740434, CODE_HUR);
        BY_UNIT_ID.put(4473177, CODE_DAY);
        BY_UNIT_ID.put(4338994, CODE_B52);
        BY_UNIT_ID.put(4403766, CODE_C26);
        BY_UNIT_ID.put(4732720, CODE_H70);
        BY_UNIT_ID.put(4340024, CODE_B98);
        BY_UNIT_ID.put(4404279, CODE_C47);
        BY_UNIT_ID.put(5719365, CODE_WEE);
        BY_UNIT_ID.put(5066574, CODE_MON);
        BY_UNIT_ID.put(4279886, CODE_ANN);
        BY_UNIT_ID.put(4469810, CODE_D42);
        BY_UNIT_ID.put(4995381, CODE_L95);
        BY_UNIT_ID.put(4995382, CODE_L96);
        BY_UNIT_ID.put(5059894, CODE_M56);
        BY_UNIT_ID.put(12865, CODE_2A);
        BY_UNIT_ID.put(5059638, CODE_M46);
        BY_UNIT_ID.put(12866, CODE_2B);
        BY_UNIT_ID.put(5059637, CODE_M45);
        BY_UNIT_ID.put(5067859, CODE_MTS);
        BY_UNIT_ID.put(4935252, CODE_KNT);
        BY_UNIT_ID.put(4934984, CODE_KMH);
        BY_UNIT_ID.put(4403510, CODE_C16);
        BY_UNIT_ID.put(12877, CODE_2M);
        BY_UNIT_ID.put(4731961, CODE_H49);
        BY_UNIT_ID.put(4732977, CODE_H81);
        BY_UNIT_ID.put(12888, CODE_2X);
        BY_UNIT_ID.put(5059897, CODE_M59);
        BY_UNIT_ID.put(4732470, CODE_H66);
        BY_UNIT_ID.put(4732471, CODE_H67);
        BY_UNIT_ID.put(18002, CODE_FR);
        BY_UNIT_ID.put(18773, CODE_IU);
        BY_UNIT_ID.put(18003, CODE_FS);
        BY_UNIT_ID.put(18509, CODE_HM);
        BY_UNIT_ID.put(4864052, CODE_J84);
        BY_UNIT_ID.put(4864053, CODE_J85);
        BY_UNIT_ID.put(4927796, CODE_K14);
        BY_UNIT_ID.put(4927800, CODE_K18);
        BY_UNIT_ID.put(4927801, CODE_K19);
        BY_UNIT_ID.put(4928567, CODE_K47);
        BY_UNIT_ID.put(4928568, CODE_K48);
        BY_UNIT_ID.put(4993330, CODE_L12);
        BY_UNIT_ID.put(4993331, CODE_L13);
        BY_UNIT_ID.put(5059122, CODE_M22);
        BY_UNIT_ID.put(5059895, CODE_M57);
        BY_UNIT_ID.put(5059896, CODE_M58);
        BY_UNIT_ID.put(5060144, CODE_M60);
        BY_UNIT_ID.put(5060145, CODE_M61);
        BY_UNIT_ID.put(5060146, CODE_M62);
        BY_UNIT_ID.put(5060147, CODE_M63);
        BY_UNIT_ID.put(5060148, CODE_M64);
        BY_UNIT_ID.put(5060149, CODE_M65);
        BY_UNIT_ID.put(5060150, CODE_M66);
        BY_UNIT_ID.put(5067595, CODE_MSK);
        BY_UNIT_ID.put(4273974, CODE_A76);
        BY_UNIT_ID.put(4403505, CODE_C11);
        BY_UNIT_ID.put(5059384, CODE_M38);
        BY_UNIT_ID.put(5059385, CODE_M39);
        BY_UNIT_ID.put(5059633, CODE_M41);
        BY_UNIT_ID.put(4273971, CODE_A73);
        BY_UNIT_ID.put(18774, CODE_IV);
        BY_UNIT_ID.put(4928560, CODE_K40);
        BY_UNIT_ID.put(5059632, CODE_M40);
        BY_UNIT_ID.put(5059634, CODE_M42);
        BY_UNIT_ID.put(4405554, CODE_C92);
        BY_UNIT_ID.put(5321522, CODE_Q32);
        BY_UNIT_ID.put(5321523, CODE_Q33);
        BY_UNIT_ID.put(5321524, CODE_Q34);
        BY_UNIT_ID.put(4282183, CODE_AWG);
        BY_UNIT_ID.put(4740186, CODE_HTZ);
        BY_UNIT_ID.put(4933722, CODE_KHZ);
        BY_UNIT_ID.put(5064794, CODE_MHZ);
        BY_UNIT_ID.put(4469305, CODE_D29);
        BY_UNIT_ID.put(4274230, CODE_A86);
        BY_UNIT_ID.put(4731184, CODE_H10);
        BY_UNIT_ID.put(4731185, CODE_H11);
        BY_UNIT_ID.put(4730937, CODE_H09);
        BY_UNIT_ID.put(4732981, CODE_H85);
        BY_UNIT_ID.put(4405559, CODE_C97);
        BY_UNIT_ID.put(5394515, CODE_RPS);
        BY_UNIT_ID.put(5394509, CODE_RPM);
        BY_UNIT_ID.put(4405556, CODE_C94);
        BY_UNIT_ID.put(4404528, CODE_C50);
        BY_UNIT_ID.put(12878, CODE_2N);
        BY_UNIT_ID.put(5060402, CODE_M72);
        BY_UNIT_ID.put(4404529, CODE_C51);
        BY_UNIT_ID.put(4933453, CODE_KGM);
        BY_UNIT_ID.put(19779, CODE_MC);
        BY_UNIT_ID.put(17482, CODE_DJ);
        BY_UNIT_ID.put(17479, CODE_DG);
        BY_UNIT_ID.put(4674125, CODE_GRM);
        BY_UNIT_ID.put(4409165, CODE_CGM);
        BY_UNIT_ID.put(5525061, CODE_TNE);
        BY_UNIT_ID.put(4478030, CODE_DTN);
        BY_UNIT_ID.put(5064525, CODE_MGM);
        BY_UNIT_ID.put(4736845, CODE_HGM);
        BY_UNIT_ID.put(4936782, CODE_KTN);
        BY_UNIT_ID.put(12885, CODE_2U);
        BY_UNIT_ID.put(4997714, CODE_LBR);
        BY_UNIT_ID.put(4674126, CODE_GRN);
        BY_UNIT_ID.put(5197402, CODE_ONZ);
        BY_UNIT_ID.put(4413257, CODE_CWI);
        BY_UNIT_ID.put(4413249, CODE_CWA);
        BY_UNIT_ID.put(5002318, CODE_LTN);
        BY_UNIT_ID.put(5461065, CODE_STI);
        BY_UNIT_ID.put(5461070, CODE_STN);
        BY_UNIT_ID.put(4280410, CODE_APZ);
        BY_UNIT_ID.put(4600115, CODE_F13);
        BY_UNIT_ID.put(4929076, CODE_K64);
        BY_UNIT_ID.put(4994617, CODE_L69);
        BY_UNIT_ID.put(4995127, CODE_L87);
        BY_UNIT_ID.put(5060662, CODE_M86);
        BY_UNIT_ID.put(4934993, CODE_KMQ);
        BY_UNIT_ID.put(12851, CODE_23);
        BY_UNIT_ID.put(4469809, CODE_D41);
        BY_UNIT_ID.put(18250, CODE_GJ);
        BY_UNIT_ID.put(4338485, CODE_B35);
        BY_UNIT_ID.put(18252, CODE_GL);
        BY_UNIT_ID.put(4274483, CODE_A93);
        BY_UNIT_ID.put(18256, CODE_GP);
        BY_UNIT_ID.put(4339506, CODE_B72);
        BY_UNIT_ID.put(4338484, CODE_B34);
        BY_UNIT_ID.put(4732468, CODE_H64);
        BY_UNIT_ID.put(4731449, CODE_H29);
        BY_UNIT_ID.put(19761, CODE_M1);
        BY_UNIT_ID.put(18257, CODE_GQ);
        BY_UNIT_ID.put(4665649, CODE_G11);
        BY_UNIT_ID.put(4666163, CODE_G33);
        BY_UNIT_ID.put(4600371, CODE_F23);
        BY_UNIT_ID.put(4665650, CODE_G12);
        BY_UNIT_ID.put(4666164, CODE_G34);
        BY_UNIT_ID.put(4665652, CODE_G14);
        BY_UNIT_ID.put(4666166, CODE_G36);
        BY_UNIT_ID.put(4665651, CODE_G13);
        BY_UNIT_ID.put(4666165, CODE_G35);
        BY_UNIT_ID.put(4665653, CODE_G15);
        BY_UNIT_ID.put(4666167, CODE_G37);
        BY_UNIT_ID.put(4666161, CODE_G31);
        BY_UNIT_ID.put(4665654, CODE_G16);
        BY_UNIT_ID.put(4666168, CODE_G38);
        BY_UNIT_ID.put(4665656, CODE_G18);
        BY_UNIT_ID.put(4666416, CODE_G40);
        BY_UNIT_ID.put(4732212, CODE_H54);
        BY_UNIT_ID.put(4732213, CODE_H55);
        BY_UNIT_ID.put(4600116, CODE_F14);
        BY_UNIT_ID.put(4600117, CODE_F15);
        BY_UNIT_ID.put(4600372, CODE_F24);
        BY_UNIT_ID.put(4665655, CODE_G17);
        BY_UNIT_ID.put(4666169, CODE_G39);
        BY_UNIT_ID.put(4732211, CODE_H53);
        BY_UNIT_ID.put(4600120, CODE_F18);
        BY_UNIT_ID.put(4600121, CODE_F19);
        BY_UNIT_ID.put(4601652, CODE_F74);
        BY_UNIT_ID.put(4601653, CODE_F75);
        BY_UNIT_ID.put(4600118, CODE_F16);
        BY_UNIT_ID.put(5060403, CODE_M73);
        BY_UNIT_ID.put(14391, CODE_87);
        BY_UNIT_ID.put(18245, CODE_GE);
        BY_UNIT_ID.put(19521, CODE_LA);
        BY_UNIT_ID.put(4666162, CODE_G32);
        BY_UNIT_ID.put(4862772, CODE_J34);
        BY_UNIT_ID.put(4862773, CODE_J35);
        BY_UNIT_ID.put(4928561, CODE_K41);
        BY_UNIT_ID.put(4929081, CODE_K69);
        BY_UNIT_ID.put(4929328, CODE_K70);
        BY_UNIT_ID.put(4929329, CODE_K71);
        BY_UNIT_ID.put(4929333, CODE_K75);
        BY_UNIT_ID.put(4929334, CODE_K76);
        BY_UNIT_ID.put(4929588, CODE_K84);
        BY_UNIT_ID.put(4993335, CODE_L17);
        BY_UNIT_ID.put(4993336, CODE_L18);
        BY_UNIT_ID.put(4993847, CODE_L37);
        BY_UNIT_ID.put(4993848, CODE_L38);
        BY_UNIT_ID.put(4993849, CODE_L39);
        BY_UNIT_ID.put(4994613, CODE_L65);
        BY_UNIT_ID.put(4994870, CODE_L76);
        BY_UNIT_ID.put(4994871, CODE_L77);
        BY_UNIT_ID.put(4995378, CODE_L92);
        BY_UNIT_ID.put(4995379, CODE_L93);
        BY_UNIT_ID.put(4929335, CODE_K77);
        BY_UNIT_ID.put(4994864, CODE_L70);
        BY_UNIT_ID.put(4995377, CODE_L91);
        BY_UNIT_ID.put(5060404, CODE_M74);
        BY_UNIT_ID.put(4404786, CODE_C62);
        BY_UNIT_ID.put(4272953, CODE_A39);
        BY_UNIT_ID.put(12850, CODE_22);
        BY_UNIT_ID.put(4732469, CODE_H65);
        BY_UNIT_ID.put(4732979, CODE_H83);
        BY_UNIT_ID.put(19288, CODE_KX);
        BY_UNIT_ID.put(4731189, CODE_H15);
        BY_UNIT_ID.put(5124664, CODE_N28);
        BY_UNIT_ID.put(5124665, CODE_N29);
        BY_UNIT_ID.put(5124912, CODE_N30);
        BY_UNIT_ID.put(19276, CODE_KL);
        BY_UNIT_ID.put(18246, CODE_GF);
        BY_UNIT_ID.put(4732726, CODE_H76);
        BY_UNIT_ID.put(19287, CODE_KW);
        BY_UNIT_ID.put(4403506, CODE_C12);
        BY_UNIT_ID.put(5059377, CODE_M31);
        BY_UNIT_ID.put(20530, CODE_P2);
        BY_UNIT_ID.put(20559, CODE_PO);
        BY_UNIT_ID.put(5060659, CODE_M83);
        BY_UNIT_ID.put(5060660, CODE_M84);
        BY_UNIT_ID.put(18255, CODE_GO);
        BY_UNIT_ID.put(12853, CODE_25);
        BY_UNIT_ID.put(4732467, CODE_H63);
        BY_UNIT_ID.put(18253, CODE_GM);
        BY_UNIT_ID.put(12856, CODE_28);
        BY_UNIT_ID.put(17461, CODE_D5);
        BY_UNIT_ID.put(20302, CODE_ON);
        BY_UNIT_ID.put(13111, CODE_37);
        BY_UNIT_ID.put(4338481, CODE_B31);
        BY_UNIT_ID.put(5060920, CODE_M98);
        BY_UNIT_ID.put(5060921, CODE_M99);
        BY_UNIT_ID.put(5124400, CODE_N10);
        BY_UNIT_ID.put(5124401, CODE_N11);
        BY_UNIT_ID.put(4338483, CODE_B33);
        BY_UNIT_ID.put(4338482, CODE_B32);
        BY_UNIT_ID.put(4600368, CODE_F20);
        BY_UNIT_ID.put(4929077, CODE_K65);
        BY_UNIT_ID.put(5129559, CODE_NEW);
        BY_UNIT_ID.put(4339507, CODE_B73);
        BY_UNIT_ID.put(4338743, CODE_B47);
        BY_UNIT_ID.put(4403760, CODE_C20);
        BY_UNIT_ID.put(4340018, CODE_B92);
        BY_UNIT_ID.put(17493, CODE_DU);
        BY_UNIT_ID.put(4405048, CODE_C78);
        BY_UNIT_ID.put(4338487, CODE_B37);
        BY_UNIT_ID.put(4338993, CODE_B51);
        BY_UNIT_ID.put(4994096, CODE_L40);
        BY_UNIT_ID.put(4995380, CODE_L94);
        BY_UNIT_ID.put(5060405, CODE_M75);
        BY_UNIT_ID.put(5060406, CODE_M76);
        BY_UNIT_ID.put(5060407, CODE_M77);
        BY_UNIT_ID.put(5060408, CODE_M78);
        BY_UNIT_ID.put(4600119, CODE_F17);
        BY_UNIT_ID.put(4600888, CODE_F48);
        BY_UNIT_ID.put(4404532, CODE_C54);
        BY_UNIT_ID.put(20053, CODE_NU);
        BY_UNIT_ID.put(4731952, CODE_H40);
        BY_UNIT_ID.put(4339508, CODE_B74);
        BY_UNIT_ID.put(4338744, CODE_B48);
        BY_UNIT_ID.put(4470835, CODE_D83);
        BY_UNIT_ID.put(4340019, CODE_B93);
        BY_UNIT_ID.put(17486, CODE_DN);
        BY_UNIT_ID.put(4863794, CODE_J72);
        BY_UNIT_ID.put(5060916, CODE_M94);
        BY_UNIT_ID.put(4601912, CODE_F88);
        BY_UNIT_ID.put(4602160, CODE_F90);
        BY_UNIT_ID.put(4601913, CODE_F89);
        BY_UNIT_ID.put(4665657, CODE_G19);
        BY_UNIT_ID.put(4600887, CODE_F47);
        BY_UNIT_ID.put(5060915, CODE_M93);
        BY_UNIT_ID.put(4731953, CODE_H41);
        BY_UNIT_ID.put(4338488, CODE_B38);
        BY_UNIT_ID.put(18753, CODE_IA);
        BY_UNIT_ID.put(13393, CODE_4Q);
        BY_UNIT_ID.put(13394, CODE_4R);
        BY_UNIT_ID.put(4600370, CODE_F22);
        BY_UNIT_ID.put(4600369, CODE_F21);
        BY_UNIT_ID.put(4665904, CODE_G20);
        BY_UNIT_ID.put(4864308, CODE_J94);
        BY_UNIT_ID.put(4994097, CODE_L41);
        BY_UNIT_ID.put(5060914, CODE_M92);
        BY_UNIT_ID.put(5060917, CODE_M95);
        BY_UNIT_ID.put(5060918, CODE_M96);
        BY_UNIT_ID.put(5060919, CODE_M97);
        BY_UNIT_ID.put(4404535, CODE_C57);
        BY_UNIT_ID.put(4404531, CODE_C53);
        BY_UNIT_ID.put(14132, CODE_74);
        BY_UNIT_ID.put(5066817, CODE_MPA);
        BY_UNIT_ID.put(5259596, CODE_PAL);
        BY_UNIT_ID.put(4935745, CODE_KPA);
        BY_UNIT_ID.put(4342098, CODE_BAR);
        BY_UNIT_ID.put(4735553, CODE_HBA);
        BY_UNIT_ID.put(5063250, CODE_MBR);
        BY_UNIT_ID.put(4932161, CODE_KBA);
        BY_UNIT_ID.put(4281421, CODE_ATM);
        BY_UNIT_ID.put(4274233, CODE_A89);
        BY_UNIT_ID.put(4340022, CODE_B96);
        BY_UNIT_ID.put(4274487, CODE_A97);
        BY_UNIT_ID.put(4732725, CODE_H75);
        BY_UNIT_ID.put(4339765, CODE_B85);
        BY_UNIT_ID.put(4404533, CODE_C55);
        BY_UNIT_ID.put(4404534, CODE_C56);
        BY_UNIT_ID.put(4730935, CODE_H07);
        BY_UNIT_ID.put(4602164, CODE_F94);
        BY_UNIT_ID.put(4602163, CODE_F93);
        BY_UNIT_ID.put(4601906, CODE_F82);
        BY_UNIT_ID.put(4601907, CODE_F83);
        BY_UNIT_ID.put(4602168, CODE_F98);
        BY_UNIT_ID.put(4602167, CODE_F97);
        BY_UNIT_ID.put(4601909, CODE_F85);
        BY_UNIT_ID.put(4602166, CODE_F96);
        BY_UNIT_ID.put(4602165, CODE_F95);
        BY_UNIT_ID.put(4601908, CODE_F84);
        BY_UNIT_ID.put(4665393, CODE_G01);
        BY_UNIT_ID.put(4602169, CODE_F99);
        BY_UNIT_ID.put(4601655, CODE_F77);
        BY_UNIT_ID.put(4534321, CODE_E01);
        BY_UNIT_ID.put(18000, CODE_FP);
        BY_UNIT_ID.put(20563, CODE_PS);
        BY_UNIT_ID.put(4338736, CODE_B40);
        BY_UNIT_ID.put(21825, CODE_UA);
        BY_UNIT_ID.put(4281428, CODE_ATT);
        BY_UNIT_ID.put(14384, CODE_80);
        BY_UNIT_ID.put(4732728, CODE_H78);
        BY_UNIT_ID.put(18512, CODE_HP);
        BY_UNIT_ID.put(18510, CODE_HN);
        BY_UNIT_ID.put(4601657, CODE_F79);
        BY_UNIT_ID.put(4601656, CODE_F78);
        BY_UNIT_ID.put(4864057, CODE_J89);
        BY_UNIT_ID.put(4928052, CODE_K24);
        BY_UNIT_ID.put(4928053, CODE_K25);
        BY_UNIT_ID.put(4928305, CODE_K31);
        BY_UNIT_ID.put(4535346, CODE_E42);
        BY_UNIT_ID.put(4535345, CODE_E41);
        BY_UNIT_ID.put(4929589, CODE_K85);
        BY_UNIT_ID.put(4929590, CODE_K86);
        BY_UNIT_ID.put(14388, CODE_84);
        BY_UNIT_ID.put(5124403, CODE_N13);
        BY_UNIT_ID.put(5124404, CODE_N14);
        BY_UNIT_ID.put(5124405, CODE_N15);
        BY_UNIT_ID.put(5124406, CODE_N16);
        BY_UNIT_ID.put(5124407, CODE_N17);
        BY_UNIT_ID.put(5124408, CODE_N18);
        BY_UNIT_ID.put(5124409, CODE_N19);
        BY_UNIT_ID.put(5124656, CODE_N20);
        BY_UNIT_ID.put(5124657, CODE_N21);
        BY_UNIT_ID.put(5124658, CODE_N22);
        BY_UNIT_ID.put(5124659, CODE_N23);
        BY_UNIT_ID.put(5124660, CODE_N24);
        BY_UNIT_ID.put(5124661, CODE_N25);
        BY_UNIT_ID.put(5124662, CODE_N26);
        BY_UNIT_ID.put(4536633, CODE_E99);
        BY_UNIT_ID.put(4599861, CODE_F05);
        BY_UNIT_ID.put(4599860, CODE_F04);
        BY_UNIT_ID.put(4599863, CODE_F07);
        BY_UNIT_ID.put(4599859, CODE_F03);
        BY_UNIT_ID.put(4994354, CODE_L52);
        BY_UNIT_ID.put(4863286, CODE_J56);
        BY_UNIT_ID.put(4405558, CODE_C96);
        BY_UNIT_ID.put(4601144, CODE_F58);
        BY_UNIT_ID.put(4339763, CODE_B83);
        BY_UNIT_ID.put(4667191, CODE_G77);
        BY_UNIT_ID.put(4470329, CODE_D69);
        BY_UNIT_ID.put(5124663, CODE_N27);
        BY_UNIT_ID.put(4404789, CODE_C65);
        BY_UNIT_ID.put(5124919, CODE_N37);
        BY_UNIT_ID.put(5124920, CODE_N38);
        BY_UNIT_ID.put(4403764, CODE_C24);
        BY_UNIT_ID.put(5124918, CODE_N36);
        BY_UNIT_ID.put(5124921, CODE_N39);
        BY_UNIT_ID.put(5125168, CODE_N40);
        BY_UNIT_ID.put(5125169, CODE_N41);
        BY_UNIT_ID.put(14393, CODE_89);
        BY_UNIT_ID.put(17207, CODE_C7);
        BY_UNIT_ID.put(4599862, CODE_F06);
        BY_UNIT_ID.put(4601910, CODE_F86);
        BY_UNIT_ID.put(4862770, CODE_J32);
        BY_UNIT_ID.put(4863795, CODE_J73);
        BY_UNIT_ID.put(4863796, CODE_J74);
        BY_UNIT_ID.put(4929079, CODE_K67);
        BY_UNIT_ID.put(4929080, CODE_K68);
        BY_UNIT_ID.put(4929841, CODE_K91);
        BY_UNIT_ID.put(4929842, CODE_K92);
        BY_UNIT_ID.put(4993333, CODE_L15);
        BY_UNIT_ID.put(4993334, CODE_L16);
        BY_UNIT_ID.put(4994612, CODE_L64);
        BY_UNIT_ID.put(5124916, CODE_N34);
        BY_UNIT_ID.put(5124917, CODE_N35);
        BY_UNIT_ID.put(5125170, CODE_N42);
        BY_UNIT_ID.put(5125171, CODE_N43);
        BY_UNIT_ID.put(5125172, CODE_N44);
        BY_UNIT_ID.put(21300, CODE_S4);
        BY_UNIT_ID.put(5060658, CODE_M82);
        BY_UNIT_ID.put(4403511, CODE_C17);
        BY_UNIT_ID.put(4666417, CODE_G41);
        BY_UNIT_ID.put(4665401, CODE_G09);
        BY_UNIT_ID.put(14641, CODE_91);
        BY_UNIT_ID.put(13379, CODE_4C);
        BY_UNIT_ID.put(4666422, CODE_G46);
        BY_UNIT_ID.put(4665648, CODE_G10);
        BY_UNIT_ID.put(21299, CODE_S3);
        BY_UNIT_ID.put(4665400, CODE_G08);
        BY_UNIT_ID.put(5060409, CODE_M79);
        BY_UNIT_ID.put(5060656, CODE_M80);
        BY_UNIT_ID.put(5060657, CODE_M81);
        BY_UNIT_ID.put(13392, CODE_4P);
        BY_UNIT_ID.put(4403762, CODE_C22);
        BY_UNIT_ID.put(5059123, CODE_M23);
        BY_UNIT_ID.put(5124913, CODE_N31);
        BY_UNIT_ID.put(17496, CODE_DX);
        BY_UNIT_ID.put(5124914, CODE_N32);
        BY_UNIT_ID.put(5124915, CODE_N33);
        BY_UNIT_ID.put(5059380, CODE_M34);
        BY_UNIT_ID.put(4869973, CODE_JOU);
        BY_UNIT_ID.put(4934223, CODE_KJO);
        BY_UNIT_ID.put(4273720, CODE_A68);
        BY_UNIT_ID.put(4404792, CODE_C68);
        BY_UNIT_ID.put(4469552, CODE_D30);
        BY_UNIT_ID.put(18262, CODE_GV);
        BY_UNIT_ID.put(13122, CODE_3B);
        BY_UNIT_ID.put(4403509, CODE_C15);
        BY_UNIT_ID.put(4273968, CODE_A70);
        BY_UNIT_ID.put(4272435, CODE_A13);
        BY_UNIT_ID.put(5720146, CODE_WHR);
        BY_UNIT_ID.put(5068616, CODE_MWH);
        BY_UNIT_ID.put(4937544, CODE_KWH);
        BY_UNIT_ID.put(4675400, CODE_GWH);
        BY_UNIT_ID.put(4469554, CODE_D32);
        BY_UNIT_ID.put(4273459, CODE_A53);
        BY_UNIT_ID.put(4339505, CODE_B71);
        BY_UNIT_ID.put(4274229, CODE_A85);
        BY_UNIT_ID.put(4338233, CODE_B29);
        BY_UNIT_ID.put(4273463, CODE_A57);
        BY_UNIT_ID.put(14389, CODE_85);
        BY_UNIT_ID.put(5125174, CODE_N46);
        BY_UNIT_ID.put(5125175, CODE_N47);
        BY_UNIT_ID.put(5723220, CODE_WTT);
        BY_UNIT_ID.put(4937556, CODE_KWT);
        BY_UNIT_ID.put(5062999, CODE_MAW);
        BY_UNIT_ID.put(4274480, CODE_A90);
        BY_UNIT_ID.put(4404017, CODE_C31);
        BY_UNIT_ID.put(4470832, CODE_D80);
        BY_UNIT_ID.put(4273715, CODE_A63);
        BY_UNIT_ID.put(4273972, CODE_A74);
        BY_UNIT_ID.put(4338489, CODE_B39);
        BY_UNIT_ID.put(18506, CODE_HJ);
        BY_UNIT_ID.put(4272693, CODE_A25);
        BY_UNIT_ID.put(4343888, CODE_BHP);
        BY_UNIT_ID.put(4927797, CODE_K15);
        BY_UNIT_ID.put(4927798, CODE_K16);
        BY_UNIT_ID.put(4928562, CODE_K42);
        BY_UNIT_ID.put(5124402, CODE_N12);
        BY_UNIT_ID.put(4933459, CODE_KGS);
        BY_UNIT_ID.put(4732214, CODE_H56);
        BY_UNIT_ID.put(5060663, CODE_M87);
        BY_UNIT_ID.put(13389, CODE_4M);
        BY_UNIT_ID.put(4600374, CODE_F26);
        BY_UNIT_ID.put(4601394, CODE_F62);
        BY_UNIT_ID.put(4600629, CODE_F35);
        BY_UNIT_ID.put(4600375, CODE_F27);
        BY_UNIT_ID.put(4601395, CODE_F63);
        BY_UNIT_ID.put(4600630, CODE_F36);
        BY_UNIT_ID.put(4600376, CODE_F28);
        BY_UNIT_ID.put(4601396, CODE_F64);
        BY_UNIT_ID.put(4600631, CODE_F37);
        BY_UNIT_ID.put(4600377, CODE_F29);
        BY_UNIT_ID.put(4601397, CODE_F65);
        BY_UNIT_ID.put(4600632, CODE_F38);
        BY_UNIT_ID.put(4600624, CODE_F30);
        BY_UNIT_ID.put(4601398, CODE_F66);
        BY_UNIT_ID.put(4600633, CODE_F39);
        BY_UNIT_ID.put(4536627, CODE_E93);
        BY_UNIT_ID.put(4601399, CODE_F67);
        BY_UNIT_ID.put(4600880, CODE_F40);
        BY_UNIT_ID.put(4600625, CODE_F31);
        BY_UNIT_ID.put(4601400, CODE_F68);
        BY_UNIT_ID.put(4600881, CODE_F41);
        BY_UNIT_ID.put(4601401, CODE_F69);
        BY_UNIT_ID.put(4600882, CODE_F42);
        BY_UNIT_ID.put(4600626, CODE_F32);
        BY_UNIT_ID.put(4601648, CODE_F70);
        BY_UNIT_ID.put(4600883, CODE_F43);
        BY_UNIT_ID.put(4601649, CODE_F71);
        BY_UNIT_ID.put(4600884, CODE_F44);
        BY_UNIT_ID.put(4600627, CODE_F33);
        BY_UNIT_ID.put(4601650, CODE_F72);
        BY_UNIT_ID.put(4600885, CODE_F45);
        BY_UNIT_ID.put(4600628, CODE_F34);
        BY_UNIT_ID.put(4601651, CODE_F73);
        BY_UNIT_ID.put(4600886, CODE_F46);
        BY_UNIT_ID.put(4600373, CODE_F25);
        BY_UNIT_ID.put(13399, CODE_4W);
        BY_UNIT_ID.put(13397, CODE_4U);
        BY_UNIT_ID.put(4929078, CODE_K66);
        BY_UNIT_ID.put(4929331, CODE_K73);
        BY_UNIT_ID.put(4929332, CODE_K74);
        BY_UNIT_ID.put(4929336, CODE_K78);
        BY_UNIT_ID.put(4929337, CODE_K79);
        BY_UNIT_ID.put(4929584, CODE_K80);
        BY_UNIT_ID.put(4929585, CODE_K81);
        BY_UNIT_ID.put(4929586, CODE_K82);
        BY_UNIT_ID.put(4929587, CODE_K83);
        BY_UNIT_ID.put(4993843, CODE_L33);
        BY_UNIT_ID.put(4993844, CODE_L34);
        BY_UNIT_ID.put(4993845, CODE_L35);
        BY_UNIT_ID.put(4993846, CODE_L36);
        BY_UNIT_ID.put(4994611, CODE_L63);
        BY_UNIT_ID.put(4994614, CODE_L66);
        BY_UNIT_ID.put(4994615, CODE_L67);
        BY_UNIT_ID.put(4994616, CODE_L68);
        BY_UNIT_ID.put(4994865, CODE_L71);
        BY_UNIT_ID.put(4994866, CODE_L72);
        BY_UNIT_ID.put(4994867, CODE_L73);
        BY_UNIT_ID.put(4534584, CODE_E18);
        BY_UNIT_ID.put(4994868, CODE_L74);
        BY_UNIT_ID.put(4994869, CODE_L75);
        BY_UNIT_ID.put(4994872, CODE_L78);
        BY_UNIT_ID.put(4994873, CODE_L79);
        BY_UNIT_ID.put(4995120, CODE_L80);
        BY_UNIT_ID.put(4995121, CODE_L81);
        BY_UNIT_ID.put(4995122, CODE_L82);
        BY_UNIT_ID.put(4995123, CODE_L83);
        BY_UNIT_ID.put(4995125, CODE_L85);
        BY_UNIT_ID.put(4995128, CODE_L88);
        BY_UNIT_ID.put(4995129, CODE_L89);
        BY_UNIT_ID.put(4995376, CODE_L90);
        BY_UNIT_ID.put(5060664, CODE_M88);
        BY_UNIT_ID.put(5060665, CODE_M89);
        BY_UNIT_ID.put(5060912, CODE_M90);
        BY_UNIT_ID.put(4862771, CODE_J33);
        BY_UNIT_ID.put(4993842, CODE_L32);
        BY_UNIT_ID.put(20033, CODE_NA);
        BY_UNIT_ID.put(5059129, CODE_M29);
        BY_UNIT_ID.put(5060913, CODE_M91);
        BY_UNIT_ID.put(5067091, CODE_MQS);
        BY_UNIT_ID.put(5067080, CODE_MQH);
        BY_UNIT_ID.put(13360, CODE_40);
        BY_UNIT_ID.put(13361, CODE_41);
        BY_UNIT_ID.put(19524, CODE_LD);
        BY_UNIT_ID.put(12874, CODE_2J);
        BY_UNIT_ID.put(13400, CODE_4X);
        BY_UNIT_ID.put(19506, CODE_L2);
        BY_UNIT_ID.put(4666423, CODE_G47);
        BY_UNIT_ID.put(4667192, CODE_G78);
        BY_UNIT_ID.put(4666929, CODE_G61);
        BY_UNIT_ID.put(4666424, CODE_G48);
        BY_UNIT_ID.put(4667193, CODE_G79);
        BY_UNIT_ID.put(4666930, CODE_G62);
        BY_UNIT_ID.put(4666425, CODE_G49);
        BY_UNIT_ID.put(4667440, CODE_G80);
        BY_UNIT_ID.put(4666931, CODE_G63);
        BY_UNIT_ID.put(4667441, CODE_G81);
        BY_UNIT_ID.put(4666932, CODE_G64);
        BY_UNIT_ID.put(4536626, CODE_E92);
        BY_UNIT_ID.put(4666674, CODE_G52);
        BY_UNIT_ID.put(4667446, CODE_G86);
        BY_UNIT_ID.put(4666937, CODE_G69);
        BY_UNIT_ID.put(4667447, CODE_G87);
        BY_UNIT_ID.put(4667184, CODE_G70);
        BY_UNIT_ID.put(4666675, CODE_G53);
        BY_UNIT_ID.put(4667448, CODE_G88);
        BY_UNIT_ID.put(4667185, CODE_G71);
        BY_UNIT_ID.put(4667449, CODE_G89);
        BY_UNIT_ID.put(4667186, CODE_G72);
        BY_UNIT_ID.put(4667442, CODE_G82);
        BY_UNIT_ID.put(4666933, CODE_G65);
        BY_UNIT_ID.put(4667443, CODE_G83);
        BY_UNIT_ID.put(4666934, CODE_G66);
        BY_UNIT_ID.put(4667444, CODE_G84);
        BY_UNIT_ID.put(4666935, CODE_G67);
        BY_UNIT_ID.put(4666673, CODE_G51);
        BY_UNIT_ID.put(4667445, CODE_G85);
        BY_UNIT_ID.put(4666936, CODE_G68);
        BY_UNIT_ID.put(4666676, CODE_G54);
        BY_UNIT_ID.put(4667696, CODE_G90);
        BY_UNIT_ID.put(4667187, CODE_G73);
        BY_UNIT_ID.put(4666677, CODE_G55);
        BY_UNIT_ID.put(4667697, CODE_G91);
        BY_UNIT_ID.put(4667188, CODE_G74);
        BY_UNIT_ID.put(4667698, CODE_G92);
        BY_UNIT_ID.put(4667189, CODE_G75);
        BY_UNIT_ID.put(4667699, CODE_G93);
        BY_UNIT_ID.put(4667190, CODE_G76);
        BY_UNIT_ID.put(12875, CODE_2K);
        BY_UNIT_ID.put(12876, CODE_2L);
        BY_UNIT_ID.put(13633, CODE_5A);
        BY_UNIT_ID.put(18226, CODE_G2);
        BY_UNIT_ID.put(18227, CODE_G3);
        BY_UNIT_ID.put(4666678, CODE_G56);
        BY_UNIT_ID.put(4666679, CODE_G57);
        BY_UNIT_ID.put(4666680, CODE_G58);
        BY_UNIT_ID.put(4666672, CODE_G50);
        BY_UNIT_ID.put(4863288, CODE_J58);
        BY_UNIT_ID.put(4863289, CODE_J59);
        BY_UNIT_ID.put(4863536, CODE_J60);
        BY_UNIT_ID.put(4863537, CODE_J61);
        BY_UNIT_ID.put(4863538, CODE_J62);
        BY_UNIT_ID.put(4863539, CODE_J63);
        BY_UNIT_ID.put(4863540, CODE_J64);
        BY_UNIT_ID.put(4863541, CODE_J65);
        BY_UNIT_ID.put(4863542, CODE_J66);
        BY_UNIT_ID.put(4863543, CODE_J67);
        BY_UNIT_ID.put(4863544, CODE_J68);
        BY_UNIT_ID.put(4863545, CODE_J69);
        BY_UNIT_ID.put(4863792, CODE_J70);
        BY_UNIT_ID.put(4863793, CODE_J71);
        BY_UNIT_ID.put(4864304, CODE_J90);
        BY_UNIT_ID.put(4864306, CODE_J92);
        BY_UNIT_ID.put(4864307, CODE_J93);
        BY_UNIT_ID.put(5125173, CODE_N45);
        BY_UNIT_ID.put(4864309, CODE_J95);
        BY_UNIT_ID.put(4864310, CODE_J96);
        BY_UNIT_ID.put(4864311, CODE_J97);
        BY_UNIT_ID.put(4864312, CODE_J98);
        BY_UNIT_ID.put(4864313, CODE_J99);
        BY_UNIT_ID.put(4927792, CODE_K10);
        BY_UNIT_ID.put(4927793, CODE_K11);
        BY_UNIT_ID.put(4927794, CODE_K12);
        BY_UNIT_ID.put(4928050, CODE_K22);
        BY_UNIT_ID.put(4928054, CODE_K26);
        BY_UNIT_ID.put(4928055, CODE_K27);
        BY_UNIT_ID.put(4928056, CODE_K28);
        BY_UNIT_ID.put(4928304, CODE_K30);
        BY_UNIT_ID.put(4928306, CODE_K32);
        BY_UNIT_ID.put(4928307, CODE_K33);
        BY_UNIT_ID.put(4928308, CODE_K34);
        BY_UNIT_ID.put(4928309, CODE_K35);
        BY_UNIT_ID.put(4928310, CODE_K36);
        BY_UNIT_ID.put(4928311, CODE_K37);
        BY_UNIT_ID.put(4928312, CODE_K38);
        BY_UNIT_ID.put(4928313, CODE_K39);
        BY_UNIT_ID.put(4929844, CODE_K94);
        BY_UNIT_ID.put(4929845, CODE_K95);
        BY_UNIT_ID.put(4929846, CODE_K96);
        BY_UNIT_ID.put(4929847, CODE_K97);
        BY_UNIT_ID.put(4929848, CODE_K98);
        BY_UNIT_ID.put(4929849, CODE_K99);
        BY_UNIT_ID.put(4993328, CODE_L10);
        BY_UNIT_ID.put(4993329, CODE_L11);
        BY_UNIT_ID.put(4994100, CODE_L44);
        BY_UNIT_ID.put(4994101, CODE_L45);
        BY_UNIT_ID.put(4994102, CODE_L46);
        BY_UNIT_ID.put(4994103, CODE_L47);
        BY_UNIT_ID.put(4994104, CODE_L48);
        BY_UNIT_ID.put(4994105, CODE_L49);
        BY_UNIT_ID.put(4994352, CODE_L50);
        BY_UNIT_ID.put(4994353, CODE_L51);
        BY_UNIT_ID.put(4994355, CODE_L53);
        BY_UNIT_ID.put(4994356, CODE_L54);
        BY_UNIT_ID.put(4994357, CODE_L55);
        BY_UNIT_ID.put(4994358, CODE_L56);
        BY_UNIT_ID.put(4994359, CODE_L57);
        BY_UNIT_ID.put(4994360, CODE_L58);
        BY_UNIT_ID.put(4994361, CODE_L59);
        BY_UNIT_ID.put(4994608, CODE_L60);
        BY_UNIT_ID.put(5058866, CODE_M12);
        BY_UNIT_ID.put(5058867, CODE_M13);
        BY_UNIT_ID.put(5058869, CODE_M15);
        BY_UNIT_ID.put(5058870, CODE_M16);
        BY_UNIT_ID.put(4732464, CODE_H60);
        BY_UNIT_ID.put(4602162, CODE_F92);
        BY_UNIT_ID.put(4602161, CODE_F91);
        BY_UNIT_ID.put(4929591, CODE_K87);
        BY_UNIT_ID.put(4929592, CODE_K88);
        BY_UNIT_ID.put(4929593, CODE_K89);
        BY_UNIT_ID.put(4929840, CODE_K90);
        BY_UNIT_ID.put(5321273, CODE_Q29);
        BY_UNIT_ID.put(4932940, CODE_KEL);
        BY_UNIT_ID.put(4408652, CODE_CEL);
        BY_UNIT_ID.put(4731186, CODE_H12);
        BY_UNIT_ID.put(4601392, CODE_F60);
        BY_UNIT_ID.put(4536632, CODE_E98);
        BY_UNIT_ID.put(4731187, CODE_H13);
        BY_UNIT_ID.put(4731188, CODE_H14);
        BY_UNIT_ID.put(4601393, CODE_F61);
        BY_UNIT_ID.put(4600112, CODE_F10);
        BY_UNIT_ID.put(4599858, CODE_F02);
        BY_UNIT_ID.put(4600113, CODE_F11);
        BY_UNIT_ID.put(4600114, CODE_F12);
        BY_UNIT_ID.put(5125945, CODE_N79);
        BY_UNIT_ID.put(4862512, CODE_J20);
        BY_UNIT_ID.put(4862513, CODE_J21);
        BY_UNIT_ID.put(4862518, CODE_J26);
        BY_UNIT_ID.put(4273208, CODE_A48);
        BY_UNIT_ID.put(4604232, CODE_FAH);
        BY_UNIT_ID.put(4862515, CODE_J23);
        BY_UNIT_ID.put(4862516, CODE_J24);
        BY_UNIT_ID.put(4862517, CODE_J25);
        BY_UNIT_ID.put(4862520, CODE_J28);
        BY_UNIT_ID.put(4862521, CODE_J29);
        BY_UNIT_ID.put(4862768, CODE_J30);
        BY_UNIT_ID.put(4405553, CODE_C91);
        BY_UNIT_ID.put(5059120, CODE_M20);
        BY_UNIT_ID.put(4404788, CODE_C64);
        BY_UNIT_ID.put(4601905, CODE_F81);
        BY_UNIT_ID.put(4863285, CODE_J55);
        BY_UNIT_ID.put(4346965, CODE_BTU);
        BY_UNIT_ID.put(16689, CODE_A1);
        BY_UNIT_ID.put(4470576, CODE_D70);
        BY_UNIT_ID.put(4862777, CODE_J39);
        BY_UNIT_ID.put(4863797, CODE_J75);
        BY_UNIT_ID.put(4928817, CODE_K51);
        BY_UNIT_ID.put(4534580, CODE_E14);
        BY_UNIT_ID.put(4928819, CODE_K53);
        BY_UNIT_ID.put(5125686, CODE_N66);
        BY_UNIT_ID.put(5125687, CODE_N67);
        BY_UNIT_ID.put(5125688, CODE_N68);
        BY_UNIT_ID.put(5125689, CODE_N69);
        BY_UNIT_ID.put(5125936, CODE_N70);
        BY_UNIT_ID.put(5125937, CODE_N71);
        BY_UNIT_ID.put(5125938, CODE_N72);
        BY_UNIT_ID.put(4469557, CODE_D35);
        BY_UNIT_ID.put(12873, CODE_2I);
        BY_UNIT_ID.put(4863028, CODE_J44);
        BY_UNIT_ID.put(4863029, CODE_J45);
        BY_UNIT_ID.put(4863031, CODE_J47);
        BY_UNIT_ID.put(4863281, CODE_J51);
        BY_UNIT_ID.put(4863282, CODE_J52);
        BY_UNIT_ID.put(4864049, CODE_J81);
        BY_UNIT_ID.put(4864050, CODE_J82);
        BY_UNIT_ID.put(4534581, CODE_E15);
        BY_UNIT_ID.put(4928820, CODE_K54);
        BY_UNIT_ID.put(4928821, CODE_K55);
        BY_UNIT_ID.put(4470068, CODE_D54);
        BY_UNIT_ID.put(5125176, CODE_N48);
        BY_UNIT_ID.put(5125177, CODE_N49);
        BY_UNIT_ID.put(5125424, CODE_N50);
        BY_UNIT_ID.put(5125425, CODE_N51);
        BY_UNIT_ID.put(5125426, CODE_N52);
        BY_UNIT_ID.put(5125427, CODE_N53);
        BY_UNIT_ID.put(5125428, CODE_N54);
        BY_UNIT_ID.put(5125429, CODE_N55);
        BY_UNIT_ID.put(5125430, CODE_N56);
        BY_UNIT_ID.put(5125431, CODE_N57);
        BY_UNIT_ID.put(4470067, CODE_D53);
        BY_UNIT_ID.put(5126192, CODE_N80);
        BY_UNIT_ID.put(5126193, CODE_N81);
        BY_UNIT_ID.put(5126194, CODE_N82);
        BY_UNIT_ID.put(4272690, CODE_A22);
        BY_UNIT_ID.put(4470577, CODE_D71);
        BY_UNIT_ID.put(4469560, CODE_D38);
        BY_UNIT_ID.put(4863024, CODE_J40);
        BY_UNIT_ID.put(4863025, CODE_J41);
        BY_UNIT_ID.put(4863026, CODE_J42);
        BY_UNIT_ID.put(4863030, CODE_J46);
        BY_UNIT_ID.put(4863032, CODE_J48);
        BY_UNIT_ID.put(4863033, CODE_J49);
        BY_UNIT_ID.put(4863800, CODE_J78);
        BY_UNIT_ID.put(4928818, CODE_K52);
        BY_UNIT_ID.put(4470069, CODE_D55);
        BY_UNIT_ID.put(5125944, CODE_N78);
        BY_UNIT_ID.put(4470578, CODE_D72);
        BY_UNIT_ID.put(4469561, CODE_D39);
        BY_UNIT_ID.put(4272688, CODE_A20);
        BY_UNIT_ID.put(4272691, CODE_A23);
        BY_UNIT_ID.put(5125940, CODE_N74);
        BY_UNIT_ID.put(5125941, CODE_N75);
        BY_UNIT_ID.put(5125942, CODE_N76);
        BY_UNIT_ID.put(5125943, CODE_N77);
        BY_UNIT_ID.put(4469049, CODE_D19);
        BY_UNIT_ID.put(4862265, CODE_J19);
        BY_UNIT_ID.put(4862514, CODE_J22);
        BY_UNIT_ID.put(4864051, CODE_J83);
        BY_UNIT_ID.put(4993332, CODE_L14);
        BY_UNIT_ID.put(4338225, CODE_B21);
        BY_UNIT_ID.put(4731701, CODE_H35);
        BY_UNIT_ID.put(5126196, CODE_N84);
        BY_UNIT_ID.put(5126197, CODE_N85);
        BY_UNIT_ID.put(5126198, CODE_N86);
        BY_UNIT_ID.put(5126199, CODE_N87);
        BY_UNIT_ID.put(5126200, CODE_N88);
        BY_UNIT_ID.put(5126201, CODE_N89);
        BY_UNIT_ID.put(4470066, CODE_D52);
        BY_UNIT_ID.put(4536631, CODE_E97);
        BY_UNIT_ID.put(4601139, CODE_F53);
        BY_UNIT_ID.put(5126195, CODE_N83);
        BY_UNIT_ID.put(19013, CODE_JE);
        BY_UNIT_ID.put(4338737, CODE_B41);
        BY_UNIT_ID.put(4863027, CODE_J43);
        BY_UNIT_ID.put(4863280, CODE_J50);
        BY_UNIT_ID.put(4863798, CODE_J76);
        BY_UNIT_ID.put(4863801, CODE_J79);
        BY_UNIT_ID.put(5125680, CODE_N60);
        BY_UNIT_ID.put(5125681, CODE_N61);
        BY_UNIT_ID.put(5125682, CODE_N62);
        BY_UNIT_ID.put(5125683, CODE_N63);
        BY_UNIT_ID.put(5125684, CODE_N64);
        BY_UNIT_ID.put(5125685, CODE_N65);
        BY_UNIT_ID.put(4337969, CODE_B11);
        BY_UNIT_ID.put(4338739, CODE_B43);
        BY_UNIT_ID.put(4272689, CODE_A21);
        BY_UNIT_ID.put(4470582, CODE_D76);
        BY_UNIT_ID.put(4469559, CODE_D37);
        BY_UNIT_ID.put(18994, CODE_J2);
        BY_UNIT_ID.put(4471093, CODE_D95);
        BY_UNIT_ID.put(19019, CODE_JK);
        BY_UNIT_ID.put(4338738, CODE_B42);
        BY_UNIT_ID.put(16730, CODE_AZ);
        BY_UNIT_ID.put(4470581, CODE_D75);
        BY_UNIT_ID.put(5125939, CODE_N73);
        BY_UNIT_ID.put(4338486, CODE_B36);
        BY_UNIT_ID.put(5125432, CODE_N58);
        BY_UNIT_ID.put(5125433, CODE_N59);
        BY_UNIT_ID.put(5321521, CODE_Q31);
        BY_UNIT_ID.put(4279632, CODE_AMP);
        BY_UNIT_ID.put(4338226, CODE_B22);
        BY_UNIT_ID.put(4731704, CODE_H38);
        BY_UNIT_ID.put(13387, CODE_4K);
        BY_UNIT_ID.put(4339764, CODE_B84);
        BY_UNIT_ID.put(4404025, CODE_C39);
        BY_UNIT_ID.put(4405040, CODE_C70);
        BY_UNIT_ID.put(5126454, CODE_N96);
        BY_UNIT_ID.put(5126455, CODE_N97);
        BY_UNIT_ID.put(4411221, CODE_COU);
        BY_UNIT_ID.put(16696, CODE_A8);
        BY_UNIT_ID.put(4731698, CODE_H32);
        BY_UNIT_ID.put(4279624, CODE_AMH);
        BY_UNIT_ID.put(5521736, CODE_TAH);
        BY_UNIT_ID.put(4470583, CODE_D77);
        BY_UNIT_ID.put(4470838, CODE_D86);
        BY_UNIT_ID.put(4338230, CODE_B26);
        BY_UNIT_ID.put(4339766, CODE_B86);
        BY_UNIT_ID.put(4404272, CODE_C40);
        BY_UNIT_ID.put(4405041, CODE_C71);
        BY_UNIT_ID.put(4534329, CODE_E09);
        BY_UNIT_ID.put(5126453, CODE_N95);
        BY_UNIT_ID.put(5126452, CODE_N94);
        BY_UNIT_ID.put(4272697, CODE_A29);
        BY_UNIT_ID.put(4274228, CODE_A84);
        BY_UNIT_ID.put(4272944, CODE_A30);
        BY_UNIT_ID.put(4339257, CODE_B69);
        BY_UNIT_ID.put(4272696, CODE_A28);
        BY_UNIT_ID.put(4338231, CODE_B27);
        BY_UNIT_ID.put(4470840, CODE_D88);
        BY_UNIT_ID.put(4339767, CODE_B87);
        BY_UNIT_ID.put(4272948, CODE_A34);
        BY_UNIT_ID.put(4339504, CODE_B70);
        BY_UNIT_ID.put(4272949, CODE_A35);
        BY_UNIT_ID.put(4272947, CODE_A33);
        BY_UNIT_ID.put(4338232, CODE_B28);
        BY_UNIT_ID.put(4470841, CODE_D89);
        BY_UNIT_ID.put(4339768, CODE_B88);
        BY_UNIT_ID.put(4470064, CODE_D50);
        BY_UNIT_ID.put(4731957, CODE_H45);
        BY_UNIT_ID.put(4469813, CODE_D45);
        BY_UNIT_ID.put(4470065, CODE_D51);
        BY_UNIT_ID.put(4731444, CODE_H24);
        BY_UNIT_ID.put(4732466, CODE_H62);
        BY_UNIT_ID.put(4731958, CODE_H46);
        BY_UNIT_ID.put(4339513, CODE_B79);
        BY_UNIT_ID.put(4338997, CODE_B55);
        BY_UNIT_ID.put(4469815, CODE_D47);
        BY_UNIT_ID.put(4404016, CODE_C30);
        BY_UNIT_ID.put(17203, CODE_C3);
        BY_UNIT_ID.put(4666928, CODE_G60);
        BY_UNIT_ID.put(5126456, CODE_N98);
        BY_UNIT_ID.put(4601911, CODE_F87);
        BY_UNIT_ID.put(4731442, CODE_H22);
        BY_UNIT_ID.put(4731443, CODE_H23);
        BY_UNIT_ID.put(5655636, CODE_VLT);
        BY_UNIT_ID.put(4339512, CODE_B78);
        BY_UNIT_ID.put(4937300, CODE_KVT);
        BY_UNIT_ID.put(12890, CODE_2Z);
        BY_UNIT_ID.put(4470834, CODE_D82);
        BY_UNIT_ID.put(5126457, CODE_N99);
        BY_UNIT_ID.put(4604242, CODE_FAR);
        BY_UNIT_ID.put(4731960, CODE_H48);
        BY_UNIT_ID.put(4403504, CODE_C10);
        BY_UNIT_ID.put(13391, CODE_4O);
        BY_UNIT_ID.put(4404273, CODE_C41);
        BY_UNIT_ID.put(13396, CODE_4T);
        BY_UNIT_ID.put(5126448, CODE_N90);
        BY_UNIT_ID.put(4273721, CODE_A69);
        BY_UNIT_ID.put(4731448, CODE_H28);
        BY_UNIT_ID.put(4731699, CODE_H33);
        BY_UNIT_ID.put(4339769, CODE_B89);
        BY_UNIT_ID.put(4404274, CODE_C42);
        BY_UNIT_ID.put(4405042, CODE_C72);
        BY_UNIT_ID.put(4272694, CODE_A26);
        BY_UNIT_ID.put(4273201, CODE_A41);
        BY_UNIT_ID.put(4731697, CODE_H31);
        BY_UNIT_ID.put(4339254, CODE_B66);
        BY_UNIT_ID.put(16695, CODE_A7);
        BY_UNIT_ID.put(16692, CODE_A4);
        BY_UNIT_ID.put(4338227, CODE_B23);
        BY_UNIT_ID.put(4666681, CODE_G59);
        BY_UNIT_ID.put(5126451, CODE_N93);
        BY_UNIT_ID.put(4601143, CODE_F57);
        BY_UNIT_ID.put(4601145, CODE_F59);
        BY_UNIT_ID.put(16709, CODE_AE);
        BY_UNIT_ID.put(4338228, CODE_B24);
        BY_UNIT_ID.put(16691, CODE_A3);
        BY_UNIT_ID.put(16690, CODE_A2);
        BY_UNIT_ID.put(4601654, CODE_F76);
        BY_UNIT_ID.put(4599864, CODE_F08);
        BY_UNIT_ID.put(5255472, CODE_P10);
        BY_UNIT_ID.put(4469555, CODE_D33);
        BY_UNIT_ID.put(4403769, CODE_C29);
        BY_UNIT_ID.put(4470833, CODE_D81);
        BY_UNIT_ID.put(4404280, CODE_C48);
        BY_UNIT_ID.put(5255475, CODE_P13);
        BY_UNIT_ID.put(5255474, CODE_P12);
        BY_UNIT_ID.put(5719362, CODE_WEB);
        BY_UNIT_ID.put(4404019, CODE_C33);
        BY_UNIT_ID.put(5255473, CODE_P11);
        BY_UNIT_ID.put(4470073, CODE_D59);
        BY_UNIT_ID.put(4338998, CODE_B56);
        BY_UNIT_ID.put(4470320, CODE_D60);
        BY_UNIT_ID.put(14385, CODE_81);
        BY_UNIT_ID.put(4403508, CODE_C14);
        BY_UNIT_ID.put(4340016, CODE_B90);
        BY_UNIT_ID.put(4404275, CODE_C43);
        BY_UNIT_ID.put(4405043, CODE_C73);
        BY_UNIT_ID.put(4730931, CODE_H03);
        BY_UNIT_ID.put(4730932, CODE_H04);
        BY_UNIT_ID.put(4667704, CODE_G98);
        BY_UNIT_ID.put(4667705, CODE_G99);
        BY_UNIT_ID.put(4730933, CODE_H05);
        BY_UNIT_ID.put(4730934, CODE_H06);
        BY_UNIT_ID.put(5255732, CODE_P24);
        BY_UNIT_ID.put(4274488, CODE_A98);
        BY_UNIT_ID.put(4340017, CODE_B91);
        BY_UNIT_ID.put(4404276, CODE_C44);
        BY_UNIT_ID.put(16693, CODE_A5);
        BY_UNIT_ID.put(16952, CODE_B8);
        BY_UNIT_ID.put(5195853, CODE_OHM);
        BY_UNIT_ID.put(4274231, CODE_A87);
        BY_UNIT_ID.put(4339509, CODE_B75);
        BY_UNIT_ID.put(4731956, CODE_H44);
        BY_UNIT_ID.put(4338745, CODE_B49);
        BY_UNIT_ID.put(4535349, CODE_E45);
        BY_UNIT_ID.put(4340020, CODE_B94);
        BY_UNIT_ID.put(5255730, CODE_P22);
        BY_UNIT_ID.put(5059126, CODE_M26);
        BY_UNIT_ID.put(5458245, CODE_SIE);
        BY_UNIT_ID.put(4338995, CODE_B53);
        BY_UNIT_ID.put(4403767, CODE_C27);
        BY_UNIT_ID.put(4340025, CODE_B99);
        BY_UNIT_ID.put(4666418, CODE_G42);
        BY_UNIT_ID.put(4666419, CODE_G43);
        BY_UNIT_ID.put(5126450, CODE_N92);
        BY_UNIT_ID.put(4404785, CODE_C61);
        BY_UNIT_ID.put(4274232, CODE_A88);
        BY_UNIT_ID.put(4339510, CODE_B76);
        BY_UNIT_ID.put(4732984, CODE_H88);
        BY_UNIT_ID.put(4338992, CODE_B50);
        BY_UNIT_ID.put(4404784, CODE_C60);
        BY_UNIT_ID.put(4403763, CODE_C23);
        BY_UNIT_ID.put(4340021, CODE_B95);
        BY_UNIT_ID.put(4404278, CODE_C46);
        BY_UNIT_ID.put(5059124, CODE_M24);
        BY_UNIT_ID.put(5255731, CODE_P23);
        BY_UNIT_ID.put(4601142, CODE_F56);
        BY_UNIT_ID.put(4731446, CODE_H26);
        BY_UNIT_ID.put(4731703, CODE_H37);
        BY_UNIT_ID.put(4601140, CODE_F54);
        BY_UNIT_ID.put(4731702, CODE_H36);
        BY_UNIT_ID.put(4601141, CODE_F55);
        BY_UNIT_ID.put(4469040, CODE_D10);
        BY_UNIT_ID.put(4731955, CODE_H43);
        BY_UNIT_ID.put(4732465, CODE_H61);
        BY_UNIT_ID.put(4339511, CODE_B77);
        BY_UNIT_ID.put(4338996, CODE_B54);
        BY_UNIT_ID.put(4666421, CODE_G45);
        BY_UNIT_ID.put(4666420, CODE_G44);
        BY_UNIT_ID.put(4994098, CODE_L42);
        BY_UNIT_ID.put(4405305, CODE_C89);
        BY_UNIT_ID.put(5255476, CODE_P14);
        BY_UNIT_ID.put(4469553, CODE_D31);
        BY_UNIT_ID.put(5255477, CODE_P15);
        BY_UNIT_ID.put(5255478, CODE_P16);
        BY_UNIT_ID.put(5255479, CODE_P17);
        BY_UNIT_ID.put(5255480, CODE_P18);
        BY_UNIT_ID.put(5255481, CODE_P19);
        BY_UNIT_ID.put(5255728, CODE_P20);
        BY_UNIT_ID.put(5255729, CODE_P21);
        BY_UNIT_ID.put(4928563, CODE_K43);
        BY_UNIT_ID.put(4404281, CODE_C49);
        BY_UNIT_ID.put(4405045, CODE_C75);
        BY_UNIT_ID.put(4469814, CODE_D46);
        BY_UNIT_ID.put(5068353, CODE_MVA);
        BY_UNIT_ID.put(4937281, CODE_KVA);
        BY_UNIT_ID.put(5059381, CODE_M35);
        BY_UNIT_ID.put(4469812, CODE_D44);
        BY_UNIT_ID.put(19253, CODE_K5);
        BY_UNIT_ID.put(4937298, CODE_KVR);
        BY_UNIT_ID.put(5062994, CODE_MAR);
        BY_UNIT_ID.put(5126449, CODE_N91);
        BY_UNIT_ID.put(5059376, CODE_M30);
        BY_UNIT_ID.put(5058871, CODE_M17);
        BY_UNIT_ID.put(5058872, CODE_M18);
        BY_UNIT_ID.put(5059127, CODE_M27);
        BY_UNIT_ID.put(5059121, CODE_M21);
        BY_UNIT_ID.put(4731700, CODE_H34);
        BY_UNIT_ID.put(4731705, CODE_H39);
        BY_UNIT_ID.put(4405300, CODE_C84);
        BY_UNIT_ID.put(19021, CODE_JM);
        BY_UNIT_ID.put(4337972, CODE_B14);
        BY_UNIT_ID.put(4337971, CODE_B13);
        BY_UNIT_ID.put(17457, CODE_D1);
        BY_UNIT_ID.put(17458, CODE_D2);
        BY_UNIT_ID.put(4405561, CODE_C99);
        BY_UNIT_ID.put(4405555, CODE_C93);
        BY_UNIT_ID.put(4731959, CODE_H47);
        BY_UNIT_ID.put(4732724, CODE_H74);
        BY_UNIT_ID.put(4535347, CODE_E43);
        BY_UNIT_ID.put(5255991, CODE_P37);
        BY_UNIT_ID.put(5255992, CODE_P38);
        BY_UNIT_ID.put(5255993, CODE_P39);
        BY_UNIT_ID.put(5256240, CODE_P40);
        BY_UNIT_ID.put(4470071, CODE_D57);
        BY_UNIT_ID.put(4470072, CODE_D58);
        BY_UNIT_ID.put(4470070, CODE_D56);
        BY_UNIT_ID.put(4469048, CODE_D18);
        BY_UNIT_ID.put(4408396, CODE_CDL);
        BY_UNIT_ID.put(5255987, CODE_P33);
        BY_UNIT_ID.put(5255988, CODE_P34);
        BY_UNIT_ID.put(5255989, CODE_P35);
        BY_UNIT_ID.put(5255990, CODE_P36);
        BY_UNIT_ID.put(5002573, CODE_LUM);
        BY_UNIT_ID.put(4339250, CODE_B62);
        BY_UNIT_ID.put(4339001, CODE_B59);
        BY_UNIT_ID.put(4272692, CODE_A24);
        BY_UNIT_ID.put(5255736, CODE_P28);
        BY_UNIT_ID.put(5255737, CODE_P29);
        BY_UNIT_ID.put(5255984, CODE_P30);
        BY_UNIT_ID.put(5255985, CODE_P31);
        BY_UNIT_ID.put(5255986, CODE_P32);
        BY_UNIT_ID.put(4339248, CODE_B60);
        BY_UNIT_ID.put(5002584, CODE_LUX);
        BY_UNIT_ID.put(4934744, CODE_KLX);
        BY_UNIT_ID.put(5255733, CODE_P25);
        BY_UNIT_ID.put(5255734, CODE_P26);
        BY_UNIT_ID.put(5255735, CODE_P27);
        BY_UNIT_ID.put(4339252, CODE_B64);
        BY_UNIT_ID.put(4339251, CODE_B63);
        BY_UNIT_ID.put(4339249, CODE_B61);
        BY_UNIT_ID.put(4469298, CODE_D22);
        BY_UNIT_ID.put(17465, CODE_D9);
        BY_UNIT_ID.put(4273712, CODE_A60);
        BY_UNIT_ID.put(4404018, CODE_C32);
        BY_UNIT_ID.put(4470837, CODE_D85);
        BY_UNIT_ID.put(4405046, CODE_C76);
        BY_UNIT_ID.put(4273716, CODE_A64);
        BY_UNIT_ID.put(4404791, CODE_C67);
        BY_UNIT_ID.put(4273456, CODE_A50);
        BY_UNIT_ID.put(4404790, CODE_C66);
        BY_UNIT_ID.put(4273458, CODE_A52);
        BY_UNIT_ID.put(5059378, CODE_M32);
        BY_UNIT_ID.put(4404536, CODE_C58);
        BY_UNIT_ID.put(4273457, CODE_A51);
        BY_UNIT_ID.put(5256243, CODE_P43);
        BY_UNIT_ID.put(4732209, CODE_H51);
        BY_UNIT_ID.put(4732210, CODE_H52);
        BY_UNIT_ID.put(5256242, CODE_P42);
        BY_UNIT_ID.put(5256241, CODE_P41);
        BY_UNIT_ID.put(4404020, CODE_C34);
        BY_UNIT_ID.put(4338741, CODE_B45);
        BY_UNIT_ID.put(4403512, CODE_C18);
        BY_UNIT_ID.put(17992, CODE_FH);
        BY_UNIT_ID.put(5256244, CODE_P44);
        BY_UNIT_ID.put(4405557, CODE_C95);
        BY_UNIT_ID.put(4470580, CODE_D74);
        BY_UNIT_ID.put(4274484, CODE_A94);
        BY_UNIT_ID.put(4273200, CODE_A40);
        BY_UNIT_ID.put(4272951, CODE_A37);
        BY_UNIT_ID.put(4272950, CODE_A36);
        BY_UNIT_ID.put(4339000, CODE_B58);
        BY_UNIT_ID.put(4337973, CODE_B15);
        BY_UNIT_ID.put(4338740, CODE_B44);
        BY_UNIT_ID.put(4337974, CODE_B16);
        BY_UNIT_ID.put(4405302, CODE_C86);
        BY_UNIT_ID.put(4732208, CODE_H50);
        BY_UNIT_ID.put(4993584, CODE_L20);
        BY_UNIT_ID.put(4928048, CODE_K20);
        BY_UNIT_ID.put(4928569, CODE_K49);
        BY_UNIT_ID.put(4929075, CODE_K63);
        BY_UNIT_ID.put(5058864, CODE_M10);
        BY_UNIT_ID.put(4404022, CODE_C36);
        BY_UNIT_ID.put(4404024, CODE_C38);
        BY_UNIT_ID.put(4404021, CODE_C35);
        BY_UNIT_ID.put(4338742, CODE_B46);
        BY_UNIT_ID.put(4536629, CODE_E95);
        BY_UNIT_ID.put(5059379, CODE_M33);
        BY_UNIT_ID.put(5256497, CODE_P51);
        BY_UNIT_ID.put(5256498, CODE_P52);
        BY_UNIT_ID.put(4928825, CODE_K59);
        BY_UNIT_ID.put(4929072, CODE_K60);
        BY_UNIT_ID.put(4929843, CODE_K93);
        BY_UNIT_ID.put(4993588, CODE_L24);
        BY_UNIT_ID.put(4993589, CODE_L25);
        BY_UNIT_ID.put(4993590, CODE_L26);
        BY_UNIT_ID.put(4993591, CODE_L27);
        BY_UNIT_ID.put(4993592, CODE_L28);
        BY_UNIT_ID.put(4993593, CODE_L29);
        BY_UNIT_ID.put(4403513, CODE_C19);
        BY_UNIT_ID.put(4471091, CODE_D93);
        BY_UNIT_ID.put(4470839, CODE_D87);
        BY_UNIT_ID.put(4732472, CODE_H68);
        BY_UNIT_ID.put(5256247, CODE_P47);
        BY_UNIT_ID.put(5256248, CODE_P48);
        BY_UNIT_ID.put(4931924, CODE_KAT);
        BY_UNIT_ID.put(4536628, CODE_E94);
        BY_UNIT_ID.put(5256245, CODE_P45);
        BY_UNIT_ID.put(5256246, CODE_P46);
        BY_UNIT_ID.put(4469811, CODE_D43);
        BY_UNIT_ID.put(4272695, CODE_A27);
        BY_UNIT_ID.put(4272946, CODE_A32);
        BY_UNIT_ID.put(4469042, CODE_D12);
        BY_UNIT_ID.put(4928824, CODE_K58);
        BY_UNIT_ID.put(4929073, CODE_K61);
        BY_UNIT_ID.put(4993587, CODE_L23);
        BY_UNIT_ID.put(4993840, CODE_L30);
        BY_UNIT_ID.put(4405298, CODE_C82);
        BY_UNIT_ID.put(4405299, CODE_C83);
        BY_UNIT_ID.put(5256249, CODE_P49);
        BY_UNIT_ID.put(5256496, CODE_P50);
        BY_UNIT_ID.put(5321520, CODE_Q30);
        BY_UNIT_ID.put(4337976, CODE_B18);
        BY_UNIT_ID.put(4272432, CODE_A10);
        BY_UNIT_ID.put(4412754, CODE_CUR);
        BY_UNIT_ID.put(5063509, CODE_MCU);
        BY_UNIT_ID.put(19765, CODE_M5);
        BY_UNIT_ID.put(12882, CODE_2R);
        BY_UNIT_ID.put(4346188, CODE_BQL);
        BY_UNIT_ID.put(4670033, CODE_GBQ);
        BY_UNIT_ID.put(12881, CODE_2Q);
        BY_UNIT_ID.put(13390, CODE_4N);
        BY_UNIT_ID.put(4730936, CODE_H08);
        BY_UNIT_ID.put(4273202, CODE_A42);
        BY_UNIT_ID.put(4272440, CODE_A18);
        BY_UNIT_ID.put(4339255, CODE_B67);
        BY_UNIT_ID.put(4338229, CODE_B25);
        BY_UNIT_ID.put(4272441, CODE_A19);
        BY_UNIT_ID.put(4272436, CODE_A14);
        BY_UNIT_ID.put(4469300, CODE_D24);
        BY_UNIT_ID.put(4272439, CODE_A17);
        BY_UNIT_ID.put(4469296, CODE_D20);
        BY_UNIT_ID.put(4272437, CODE_A15);
        BY_UNIT_ID.put(4469046, CODE_D16);
        BY_UNIT_ID.put(4469301, CODE_D25);
        BY_UNIT_ID.put(4272438, CODE_A16);
        BY_UNIT_ID.put(4469047, CODE_D17);
        BY_UNIT_ID.put(4339761, CODE_B81);
        BY_UNIT_ID.put(4273717, CODE_A65);
        BY_UNIT_ID.put(4469297, CODE_D21);
        BY_UNIT_ID.put(4337970, CODE_B12);
        BY_UNIT_ID.put(4273460, CODE_A54);
        BY_UNIT_ID.put(4273464, CODE_A58);
        BY_UNIT_ID.put(4470579, CODE_D73);
        BY_UNIT_ID.put(4273461, CODE_A55);
        BY_UNIT_ID.put(4273718, CODE_A66);
        BY_UNIT_ID.put(4338224, CODE_B20);
        BY_UNIT_ID.put(4273462, CODE_A56);
        BY_UNIT_ID.put(4273719, CODE_A67);
        BY_UNIT_ID.put(4469302, CODE_D26);
        BY_UNIT_ID.put(4732216, CODE_H58);
        BY_UNIT_ID.put(4405303, CODE_C87);
        BY_UNIT_ID.put(4274485, CODE_A95);
        BY_UNIT_ID.put(4403507, CODE_C13);
        BY_UNIT_ID.put(4405296, CODE_C80);
        BY_UNIT_ID.put(4273713, CODE_A61);
        BY_UNIT_ID.put(4469043, CODE_D13);
        BY_UNIT_ID.put(4403768, CODE_C28);
        BY_UNIT_ID.put(4471089, CODE_D91);
        BY_UNIT_ID.put(4993841, CODE_L31);
        BY_UNIT_ID.put(4274486, CODE_A96);
        BY_UNIT_ID.put(4273714, CODE_A62);
        BY_UNIT_ID.put(4410183, CODE_CKG);
        BY_UNIT_ID.put(17208, CODE_C8);
        BY_UNIT_ID.put(12867, CODE_2C);
        BY_UNIT_ID.put(12889, CODE_2Y);
        BY_UNIT_ID.put(4863283, CODE_J53);
        BY_UNIT_ID.put(19282, CODE_KR);
        BY_UNIT_ID.put(4272945, CODE_A31);
        BY_UNIT_ID.put(17462, CODE_D6);
        BY_UNIT_ID.put(5256500, CODE_P54);
        BY_UNIT_ID.put(5256501, CODE_P55);
        BY_UNIT_ID.put(5256502, CODE_P56);
        BY_UNIT_ID.put(5256503, CODE_P57);
        BY_UNIT_ID.put(5256504, CODE_P58);
        BY_UNIT_ID.put(5256505, CODE_P59);
        BY_UNIT_ID.put(5256752, CODE_P60);
        BY_UNIT_ID.put(5256753, CODE_P61);
        BY_UNIT_ID.put(5256754, CODE_P62);
        BY_UNIT_ID.put(5256755, CODE_P63);
        BY_UNIT_ID.put(5256756, CODE_P64);
        BY_UNIT_ID.put(5256757, CODE_P65);
        BY_UNIT_ID.put(5256758, CODE_P66);
        BY_UNIT_ID.put(5256759, CODE_P67);
        BY_UNIT_ID.put(5256760, CODE_P68);
        BY_UNIT_ID.put(5256761, CODE_P69);
        BY_UNIT_ID.put(5257008, CODE_P70);
        BY_UNIT_ID.put(5257009, CODE_P71);
        BY_UNIT_ID.put(5257010, CODE_P72);
        BY_UNIT_ID.put(5257011, CODE_P73);
        BY_UNIT_ID.put(5257012, CODE_P74);
        BY_UNIT_ID.put(5257013, CODE_P75);
        BY_UNIT_ID.put(5257014, CODE_P76);
        BY_UNIT_ID.put(5257015, CODE_P77);
        BY_UNIT_ID.put(5257016, CODE_P78);
        BY_UNIT_ID.put(5256499, CODE_P53);
        BY_UNIT_ID.put(4405301, CODE_C85);
        BY_UNIT_ID.put(4471092, CODE_D94);
        BY_UNIT_ID.put(4405552, CODE_C90);
        BY_UNIT_ID.put(4405304, CODE_C88);
        BY_UNIT_ID.put(4272952, CODE_A38);
        BY_UNIT_ID.put(4469816, CODE_D48);
        BY_UNIT_ID.put(4469817, CODE_D49);
        BY_UNIT_ID.put(16694, CODE_A6);
        BY_UNIT_ID.put(13107, CODE_33);
        BY_UNIT_ID.put(5257017, CODE_P79);
        BY_UNIT_ID.put(13108, CODE_34);
        BY_UNIT_ID.put(4731954, CODE_H42);
        BY_UNIT_ID.put(4732473, CODE_H69);
        BY_UNIT_ID.put(5257264, CODE_P80);
        BY_UNIT_ID.put(5257265, CODE_P81);
        BY_UNIT_ID.put(5257266, CODE_P82);
        BY_UNIT_ID.put(5257267, CODE_P83);
        BY_UNIT_ID.put(5257268, CODE_P84);
        BY_UNIT_ID.put(5257269, CODE_P85);
        BY_UNIT_ID.put(5257270, CODE_P86);
        BY_UNIT_ID.put(13109, CODE_35);
        BY_UNIT_ID.put(5257271, CODE_P87);
        BY_UNIT_ID.put(5197901, CODE_OPM);
        BY_UNIT_ID.put(4935245, CODE_KNM);
        BY_UNIT_ID.put(5321525, CODE_Q35);
        BY_UNIT_ID.put(13112, CODE_38);
        BY_UNIT_ID.put(13625, CODE_59);
        BY_UNIT_ID.put(13873, CODE_61);
        BY_UNIT_ID.put(13878, CODE_66);
        BY_UNIT_ID.put(14134, CODE_76);
        BY_UNIT_ID.put(14136, CODE_78);
        BY_UNIT_ID.put(12871, CODE_2G);
        BY_UNIT_ID.put(12872, CODE_2H);
        BY_UNIT_ID.put(12880, CODE_2P);
        BY_UNIT_ID.put(13388, CODE_4L);
        BY_UNIT_ID.put(4273203, CODE_A43);
        BY_UNIT_ID.put(4273207, CODE_A47);
        BY_UNIT_ID.put(4273209, CODE_A49);
        BY_UNIT_ID.put(4274489, CODE_A99);
        BY_UNIT_ID.put(16706, CODE_AB);
        BY_UNIT_ID.put(16708, CODE_AD);
        BY_UNIT_ID.put(16945, CODE_B1);
        BY_UNIT_ID.put(4337968, CODE_B10);
        BY_UNIT_ID.put(4338480, CODE_B30);
        BY_UNIT_ID.put(4339253, CODE_B65);
        BY_UNIT_ID.put(4339256, CODE_B68);
        BY_UNIT_ID.put(4339760, CODE_B80);
        BY_UNIT_ID.put(4343380, CODE_BFT);
        BY_UNIT_ID.put(4345933, CODE_BPM);
        BY_UNIT_ID.put(4403761, CODE_C21);
        BY_UNIT_ID.put(4404023, CODE_C37);
        BY_UNIT_ID.put(4405044, CODE_C74);
        BY_UNIT_ID.put(4405049, CODE_C79);
        BY_UNIT_ID.put(4468787, CODE_D03);
        BY_UNIT_ID.put(4469041, CODE_D11);
        BY_UNIT_ID.put(4469556, CODE_D34);
        BY_UNIT_ID.put(4469558, CODE_D36);
        BY_UNIT_ID.put(4470584, CODE_D78);
        BY_UNIT_ID.put(4479566, CODE_DZN);
        BY_UNIT_ID.put(4534327, CODE_E07);
        BY_UNIT_ID.put(4534328, CODE_E08);
        BY_UNIT_ID.put(4534576, CODE_E10);
        BY_UNIT_ID.put(4534582, CODE_E16);
        BY_UNIT_ID.put(4534583, CODE_E17);
        BY_UNIT_ID.put(4534832, CODE_E20);
        BY_UNIT_ID.put(4535089, CODE_E31);
        BY_UNIT_ID.put(4535090, CODE_E32);
        BY_UNIT_ID.put(4535092, CODE_E34);
        BY_UNIT_ID.put(4535093, CODE_E35);
        BY_UNIT_ID.put(4535094, CODE_E36);
        BY_UNIT_ID.put(4535097, CODE_E39);
        BY_UNIT_ID.put(4535344, CODE_E40);
        BY_UNIT_ID.put(4535348, CODE_E44);
        BY_UNIT_ID.put(4535350, CODE_E46);
        BY_UNIT_ID.put(4535351, CODE_E47);
        BY_UNIT_ID.put(4535608, CODE_E58);
        BY_UNIT_ID.put(4535609, CODE_E59);
        BY_UNIT_ID.put(4535856, CODE_E60);
        BY_UNIT_ID.put(4535857, CODE_E61);
        BY_UNIT_ID.put(4535858, CODE_E62);
        BY_UNIT_ID.put(4535859, CODE_E63);
        BY_UNIT_ID.put(4535860, CODE_E64);
        BY_UNIT_ID.put(4535861, CODE_E65);
        BY_UNIT_ID.put(4535862, CODE_E66);
        BY_UNIT_ID.put(4535863, CODE_E67);
        BY_UNIT_ID.put(4535864, CODE_E68);
        BY_UNIT_ID.put(4535865, CODE_E69);
        BY_UNIT_ID.put(4536112, CODE_E70);
        BY_UNIT_ID.put(4536113, CODE_E71);
        BY_UNIT_ID.put(4536114, CODE_E72);
        BY_UNIT_ID.put(4536115, CODE_E73);
        BY_UNIT_ID.put(4536116, CODE_E74);
        BY_UNIT_ID.put(4536117, CODE_E75);
        BY_UNIT_ID.put(4536118, CODE_E76);
        BY_UNIT_ID.put(4536119, CODE_E77);
        BY_UNIT_ID.put(4536120, CODE_E78);
        BY_UNIT_ID.put(4536121, CODE_E79);
        BY_UNIT_ID.put(4536368, CODE_E80);
        BY_UNIT_ID.put(4536369, CODE_E81);
        BY_UNIT_ID.put(4536370, CODE_E82);
        BY_UNIT_ID.put(4536371, CODE_E83);
        BY_UNIT_ID.put(4536372, CODE_E84);
        BY_UNIT_ID.put(4536373, CODE_E85);
        BY_UNIT_ID.put(4536374, CODE_E86);
        BY_UNIT_ID.put(4536375, CODE_E87);
        BY_UNIT_ID.put(4536376, CODE_E88);
        BY_UNIT_ID.put(4536377, CODE_E89);
        BY_UNIT_ID.put(4536624, CODE_E90);
        BY_UNIT_ID.put(4536625, CODE_E91);
        BY_UNIT_ID.put(4599857, CODE_F01);
        BY_UNIT_ID.put(17987, CODE_FC);
        BY_UNIT_ID.put(4606292, CODE_FIT);
        BY_UNIT_ID.put(18242, CODE_GB);
        BY_UNIT_ID.put(4671049, CODE_GFI);
        BY_UNIT_ID.put(4671809, CODE_GIA);
        BY_UNIT_ID.put(4671817, CODE_GII);
        BY_UNIT_ID.put(4674127, CODE_GRO);
        BY_UNIT_ID.put(4731445, CODE_H25);
        BY_UNIT_ID.put(4732721, CODE_H71);
        BY_UNIT_ID.put(4732722, CODE_H72);
        BY_UNIT_ID.put(4732723, CODE_H73);
        BY_UNIT_ID.put(4732727, CODE_H77);
        BY_UNIT_ID.put(4732976, CODE_H80);
        BY_UNIT_ID.put(4732978, CODE_H82);
        BY_UNIT_ID.put(4732985, CODE_H89);
        BY_UNIT_ID.put(4733232, CODE_H90);
        BY_UNIT_ID.put(4733233, CODE_H91);
        BY_UNIT_ID.put(4733234, CODE_H92);
        BY_UNIT_ID.put(4733235, CODE_H93);
        BY_UNIT_ID.put(4733236, CODE_H94);
        BY_UNIT_ID.put(4733237, CODE_H95);
        BY_UNIT_ID.put(4733238, CODE_H96);
        BY_UNIT_ID.put(4733240, CODE_H98);
        BY_UNIT_ID.put(4733241, CODE_H99);
        BY_UNIT_ID.put(4738385, CODE_HMQ);
        BY_UNIT_ID.put(4862256, CODE_J10);
        BY_UNIT_ID.put(4862258, CODE_J12);
        BY_UNIT_ID.put(4862259, CODE_J13);
        BY_UNIT_ID.put(4862260, CODE_J14);
        BY_UNIT_ID.put(4862261, CODE_J15);
        BY_UNIT_ID.put(4862262, CODE_J16);
        BY_UNIT_ID.put(4862263, CODE_J17);
        BY_UNIT_ID.put(4862264, CODE_J18);
        BY_UNIT_ID.put(4862519, CODE_J27);
        BY_UNIT_ID.put(4862769, CODE_J31);
        BY_UNIT_ID.put(4862776, CODE_J38);
        BY_UNIT_ID.put(4863284, CODE_J54);
        BY_UNIT_ID.put(19251, CODE_K3);
        BY_UNIT_ID.put(4928816, CODE_K50);
        BY_UNIT_ID.put(4932419, CODE_KCC);
        BY_UNIT_ID.put(4932695, CODE_KDW);
        BY_UNIT_ID.put(4933721, CODE_KHY);
        BY_UNIT_ID.put(4934977, CODE_KMA);
        BY_UNIT_ID.put(4935241, CODE_KNI);
        BY_UNIT_ID.put(4935752, CODE_KPH);
        BY_UNIT_ID.put(4935759, CODE_KPO);
        BY_UNIT_ID.put(4936516, CODE_KSD);
        BY_UNIT_ID.put(4936520, CODE_KSH);
        BY_UNIT_ID.put(4937042, CODE_KUR);
        BY_UNIT_ID.put(4937561, CODE_KWY);
        BY_UNIT_ID.put(4937551, CODE_KWO);
        BY_UNIT_ID.put(5058873, CODE_M19);
        BY_UNIT_ID.put(5059125, CODE_M25);
        BY_UNIT_ID.put(5059382, CODE_M36);
        BY_UNIT_ID.put(5059383, CODE_M37);
        BY_UNIT_ID.put(19769, CODE_M9);
        BY_UNIT_ID.put(5062984, CODE_MAH);
        BY_UNIT_ID.put(5130572, CODE_NIL);
        BY_UNIT_ID.put(20056, CODE_NX);
        BY_UNIT_ID.put(20529, CODE_P1);
        BY_UNIT_ID.put(5257272, CODE_P88);
        BY_UNIT_ID.put(5257273, CODE_P89);
        BY_UNIT_ID.put(5257520, CODE_P90);
        BY_UNIT_ID.put(5257521, CODE_P91);
        BY_UNIT_ID.put(5257522, CODE_P92);
        BY_UNIT_ID.put(5257523, CODE_P93);
        BY_UNIT_ID.put(5257524, CODE_P94);
        BY_UNIT_ID.put(5257525, CODE_P95);
        BY_UNIT_ID.put(5257526, CODE_P96);
        BY_UNIT_ID.put(5257527, CODE_P97);
        BY_UNIT_ID.put(5257528, CODE_P98);
        BY_UNIT_ID.put(5257529, CODE_P99);
        BY_UNIT_ID.put(5262401, CODE_PLA);
        BY_UNIT_ID.put(20561, CODE_PQ);
        BY_UNIT_ID.put(5264462, CODE_PTN);
        BY_UNIT_ID.put(5321008, CODE_Q10);
        BY_UNIT_ID.put(5321009, CODE_Q11);
        BY_UNIT_ID.put(5321010, CODE_Q12);
        BY_UNIT_ID.put(5321011, CODE_Q13);
        BY_UNIT_ID.put(5321012, CODE_Q14);
        BY_UNIT_ID.put(5321013, CODE_Q15);
        BY_UNIT_ID.put(5321014, CODE_Q16);
        BY_UNIT_ID.put(5321015, CODE_Q17);
        BY_UNIT_ID.put(5321016, CODE_Q18);
        BY_UNIT_ID.put(5321017, CODE_Q19);
        BY_UNIT_ID.put(5321264, CODE_Q20);
        BY_UNIT_ID.put(5321265, CODE_Q21);
        BY_UNIT_ID.put(5321266, CODE_Q22);
        BY_UNIT_ID.put(5321267, CODE_Q23);
        BY_UNIT_ID.put(5321268, CODE_Q24);
        BY_UNIT_ID.put(5321269, CODE_Q25);
        BY_UNIT_ID.put(5321270, CODE_Q26);
        BY_UNIT_ID.put(5321271, CODE_Q27);
        BY_UNIT_ID.put(5321272, CODE_Q28);
        BY_UNIT_ID.put(5321526, CODE_Q36);
        BY_UNIT_ID.put(20818, CODE_QR);
        BY_UNIT_ID.put(5330002, CODE_QTR);
        BY_UNIT_ID.put(5521742, CODE_TAN);
        BY_UNIT_ID.put(5524301, CODE_TKM);
        BY_UNIT_ID.put(5525577, CODE_TPI);
        BY_UNIT_ID.put(5525828, CODE_TQD);
        BY_UNIT_ID.put(22081, CODE_VA);
        BY_UNIT_ID.put(22337, CODE_WA);
        BY_UNIT_ID.put(5722948, CODE_WSD);
        BY_UNIT_ID.put(5067351, CODE_MRW);
        BY_UNIT_ID.put(5065559, CODE_MKW);
        BY_UNIT_ID.put(5067095, CODE_MQW);
        BY_UNIT_ID.put(4740933, CODE_HWE);
        BY_UNIT_ID.put(5067332, CODE_MRD);
        BY_UNIT_ID.put(5065540, CODE_MKD);
        BY_UNIT_ID.put(5067076, CODE_MQD);
        BY_UNIT_ID.put(4735300, CODE_HAD);
        BY_UNIT_ID.put(5067341, CODE_MRM);
        BY_UNIT_ID.put(5065549, CODE_MKM);
        BY_UNIT_ID.put(5067085, CODE_MQM);
        BY_UNIT_ID.put(4738383, CODE_HMO);
        BY_UNIT_ID.put(4473431, CODE_DBW);
        BY_UNIT_ID.put(4473421, CODE_DBM);
        BY_UNIT_ID.put(4607573, CODE_FNU);
        BY_UNIT_ID.put(5133397, CODE_NTU);
    }

    private CefactEngineeringUnits() {
    }

    public static EUInformation[] getAll() {
        return BY_UNIT_ID.values()
            .toArray(new EUInformation[0]);
    }

    public static EUInformation getByUnitId(int unitId) {
        return BY_UNIT_ID.get(unitId);
    }
}
