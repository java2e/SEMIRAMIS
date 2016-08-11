package pelops.reports.controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;

import pelops.dao.DosyaEklemeDAO;
import pelops.db.DBConnection;
import pelops.report.model.AkbankHesap;
import pelops.report.model.Model;
import pelops.report.model.ReportGenel;
import pelops.report.model.ReportUtils;
import pelops.report.model.SearchParams;

public class GenelRaporDAO extends DBConnection {

	Statement stm;
	ResultSet rs;
	String SQL;
	HashSet<Integer> idList;
	public static HashMap<Integer, ReportGenel> trnList = new HashMap<Integer, ReportGenel>();
	public static ArrayList<ReportGenel> returnList = new ArrayList<ReportGenel>();

	public ArrayList<ReportGenel> getReportFromIcraDosyaID(int id) throws Exception {
		ArrayList<ReportGenel> ttList = new ArrayList<ReportGenel>();
		SQL = "SELECT id, icra_dosyasi_no, muvekkil_adi, vergi_no, vergi_dairesi, muvekkil_adres, barkod,"
				+ " borclu_adi, borclu_adres, talep_edilen_hak, takip_yolu, vekil_adi, asil_alacak, "
				+ "temerrut_faizi, faiz_gider_vergisi, noter_masrafi, icra_bilgi, gecikme_faizi, diger, "
				+ "faiz_gider_vergisi, icra_hesap, tapu_bilgi, borclu_tc, borclu_dogum, il "
				+ "FROM vwreport  where id =" + id + ";";

		newConnectDB();

		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		idList = new HashSet<Integer>();

		while (rs.next()) {
			String text = null;
			ReportGenel tt = new ReportGenel();
			NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
			tt.setId(rs.getInt("id"));
			tt.setBorclu_id(rs.getInt("id"));
			idList.add(rs.getInt("id"));
			tt.setIcraDosyaNo(rs.getString("icra_dosyasi_no"));
			text = rs.getString("muvekkil_adi") + " " + rs.getString("vergi_dairesi") + " " + rs.getString("vergi_no")
					+ " " + rs.getString("muvekkil_adres");
			tt.setAlacakliAdi(text);
			tt.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			tt.setAlacakli(rs.getString("muvekkil_adi"));
			text = null;
			text = rs.getString("borclu_adi") + " TC NO:" + rs.getString("borclu_tc") + " "
					+ (rs.getString("borclu_adres") == null ? " " : rs.getString("borclu_adres"));
			tt.setBorclu(text);
			text = null;
			tt.setAsilAlacak1(defaultFormat.format(rs.getDouble("asil_alacak")));
			tt.setGiderVergisi1(defaultFormat.format(rs.getDouble("faiz_gider_vergisi")));
			tt.setIcraMdHesapNo(rs.getString("icra_hesap"));
			// tt.setTakipAlacagi(defaultFormat.format(rs.getDouble("faiz_gider_vergisi")));
			tt.setTakipYolu(rs.getString("takip_yolu"));
			tt.setTalepYazisi(rs.getString("talep_edilen_hak"));
			tt.setTalepYaziEk(rs.getString("talep_edilen_hak"));
			tt.setFaizGider1(defaultFormat.format(rs.getDouble("gecikme_faizi")));
			tt.setNoterMasrafi1(defaultFormat.format(rs.getDouble("noter_masrafi")));
			tt.setTemmerutFaizi1(defaultFormat.format(rs.getDouble("temerrut_faizi")));
			tt.setVekil(rs.getString("vekil_adi"));
			tt.setIcraBilgi(rs.getString("icra_bilgi"));
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			tt.setTarih(String.valueOf(dateFormat.format(date)));
			Double toplam = rs.getDouble("asil_alacak") + rs.getDouble("noter_masrafi") + rs.getDouble("temerrut_faizi")
					+ rs.getDouble("gecikme_faizi") + rs.getDouble("faiz_gider_vergisi");
			tt.setGecikmeFaizi1(rs.getString("gecikme_faizi"));
			tt.setToplam(defaultFormat.format(toplam));
			String ek = "Borca Iliskin Ihtarname ve Eki Hesap Özeti".toString();
			tt.setDiger1(rs.getString("diger"));
			tt.setEk(ek);
			tt.setBorcluAdi(rs.getString("borclu_adi"));
			tt.setId(rs.getInt("id"));
			tt.setBarkot(rs.getString("barkod"));
			tt.setIl(rs.getString("il"));
			tt.setBorcluAdres(rs.getString("borclu_adres"));

			// if (rs.getString("borclu_adres") != null) {
			// String[] adres = rs.getString("borclu_adres").split(" ");
			// int i = adres.length - 1;
			// String il = adres[i];
			// tt.setIl(il);
			// }
			tt.setKonu("Ödeme Emri");

			// :TODO borclu bilgisi nereden dolacak öğren ona göre değişiklik
			// yap
			tt.setBorclu_bilgi(new ArrayList<String>());

			ttList.add(tt);

		}

		disconnectDB();

		checkAttributeForNull(ttList);
		return ttList;
	}

	public ArrayList<ReportGenel> getFilteredReports1(SearchParams params) throws Exception {
		ArrayList<ReportGenel> genels = new ArrayList<ReportGenel>();
		String oldDate = "01/01/1900";
		@SuppressWarnings("deprecation")
		Date tarih = new Date(oldDate);
		String fullSQL = "";
		SQL = "SELECT id, icra_dosyasi_no, eklemet, muvekkil_adi, vergi_no, vergi_dairesi, muvekkil_adres, barkod,"
				+ " borclu_adi, borclu_adres, talep_edilen_hak, takip_yolu, vekil_adi, diger, "
				+ "asil_alacak, temerrut_faizi, faiz_gider_vergisi, gecikme_faizi, "
				+ " noter_masrafi, faiz_gider_v, icra_bilgi, icra_hesap, tapu_bilgi, "
				+ "  borclu_tc, borclu_dogum , il  FROM vwreport where 1=1";

		if (!params.getTarih1().equals(tarih)) {
			fullSQL += " and eklemet between '" + params.getTarih1() + "' and '" + params.getTarih2() + "'";
		}

		if (params.getBorcluAdi() != "") {
			fullSQL += "and borclu_adi='" + params.getBorcluAdi() + "'";
		}

		if (params.getMuvekkilAdi() != "") {
			fullSQL += "and muvekkil_adi='" + params.getMuvekkilAdi() + "'";
		}

		if (params.getIcraDosyaNo() != "") {
			fullSQL += "and icra_dosyasi_no='" + params.getIcraDosyaNo() + "'";
		}

		if (params.getIcraMudurlugu() != "") {
			fullSQL += "and icra_bilgi='" + params.getIcraMudurlugu() + "'";
		}

		if (fullSQL == "") {
			fullSQL = SQL + ";";
		} else {
			fullSQL = SQL + fullSQL + ";";
		}

		newConnectDB();

		stm = conn.createStatement();
		rs = stm.executeQuery(fullSQL);
		idList = new HashSet<Integer>();
		while (rs.next()) {
			String text = null;
			ReportGenel tt = new ReportGenel();
			NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
			tt.setId(rs.getInt("id"));
			tt.setBorclu_id(rs.getInt("id"));
			idList.add(rs.getInt("id"));
			tt.setIcraDosyaNo(rs.getString("icra_dosyasi_no"));
			text = rs.getString("muvekkil_adi") + " " + rs.getString("vergi_dairesi") + " " + rs.getString("vergi_no")
					+ " " + rs.getString("muvekkil_adres");
			tt.setAlacakliAdi(text);
			tt.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			text = null;
			text = rs.getString("borclu_adi") + " TC NO:" + rs.getString("borclu_tc") + " "
					+ (rs.getString("borclu_adres") == null ? " " : rs.getString("borclu_adres"));
			tt.setBorclu(text);
			text = null;
			tt.setAsilAlacak1(defaultFormat.format(rs.getDouble("asil_alacak")));
			tt.setGiderVergisi1(defaultFormat.format(rs.getDouble("faiz_gider_vergisi")));
			// tt.setTakipAlacagi(defaultFormat.format(rs.getDouble("faiz_gider_vergisi")));
			tt.setTakipYolu(rs.getString("takip_yolu"));
			tt.setTalepYazisi(rs.getString("talep_edilen_hak"));
			tt.setTalepYaziEk(rs.getString("talep_edilen_hak"));
			tt.setFaizGider1(defaultFormat.format(rs.getDouble("gecikme_faizi")));
			tt.setNoterMasrafi1(defaultFormat.format(rs.getDouble("noter_masrafi")));
			tt.setIcraMdHesapNo(rs.getString("icra_hesap"));
			tt.setId(rs.getInt("id"));

			tt.setTemmerutFaizi1(defaultFormat.format(rs.getDouble("temerrut_faizi")));
			tt.setVekil(rs.getString("vekil_adi"));
			tt.setIcraBilgi(rs.getString("icra_bilgi"));
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			tt.setTarih(String.valueOf(dateFormat.format(date)));
			Double toplam = rs.getDouble("asil_alacak") + rs.getDouble("noter_masrafi") + rs.getDouble("temerrut_faizi")
					+ rs.getDouble("gecikme_faizi") + rs.getDouble("faiz_gider_vergisi");
			tt.setGecikmeFaizi1(rs.getString("gecikme_faizi"));
			tt.setToplam(defaultFormat.format(toplam));
			String ek = "Borca Iliskin Ihtarname ve Eki Hesap Özeti".toString();
			tt.setBarkot(rs.getString("barkod"));
			tt.setEk(ek);
			tt.setDiger1(rs.getString("diger"));
			tt.setBorcluAdi(rs.getString("borclu_adi"));
			tt.setIl(rs.getString("il"));
			tt.setBorcluAdres(rs.getString("borclu_adres"));

			// if (rs.getString("borclu_adres") != null) {
			// String[] adres = rs.getString("borclu_adres").split(" ");
			// int i = adres.length - 1;
			// String il = adres[i];
			// tt.setIl(il);
			// }
			tt.setKonu("Ödeme Emri");
			// :TODO borclu bilgisi nereden dolacak öğren ona göre değişiklik
			// yap
			tt.setBorclu_bilgi(new ArrayList<String>());

			genels.add(tt);

		}

		return genels;

	}

	private HashSet<Integer> getIDList(SearchParams params, Integer ID) throws Exception {
		HashSet<Integer> idListe = new HashSet<Integer>();

		if (ID == null) {
			String oldDate = "01/01/1900";
			@SuppressWarnings("deprecation")
			Date tarih = new Date(oldDate);
			String fullSQL = "";
			SQL = "SELECT id  FROM vwreport where 1=1";

			if (!params.getTarih1().equals(tarih)) {
				fullSQL += " and eklemet between '" + params.getTarih1() + "' and '" + params.getTarih2() + "'";
			}

			if (params.getBorcluAdi() != "") {
				fullSQL += "and borclu_adi='" + params.getBorcluAdi() + "'";
			}

			if (params.getMuvekkilAdi() != "") {
				fullSQL += "and muvekkil_adi='" + params.getMuvekkilAdi() + "'";
			}

			if (params.getIcraDosyaNo() != "") {
				fullSQL += "and icra_dosyasi_no='" + params.getIcraDosyaNo() + "'";
			}

			if (params.getIcraMudurlugu() != "") {
				fullSQL += "and icra_bilgi='" + params.getIcraMudurlugu() + "'";
			}

			if (fullSQL == "") {
				fullSQL = SQL + ";";
				// System.out.println("burda" + fullSQL);
			} else {
				fullSQL = SQL + fullSQL + ";";
			}

			newConnectDB();

			stm = conn.createStatement();
			rs = stm.executeQuery(fullSQL);
			while (rs.next()) {

				idListe.add(rs.getInt("id"));

			}
		} 
		
		else
		{
			idListe.add(ID);
		}
		disconnectDB();

		return idListe;

	}

	public HashMap<Integer, ReportGenel> getTrnList(SearchParams params, Integer ID) throws Exception {
		ArrayList<ReportGenel> genelList;
		if (ID == null) {
			genelList = getFilteredReports1(params);
		} else {
			genelList = getReportFromIcraDosyaID(ID);
		}
		HashMap<Integer, ReportGenel> trnListe = new HashMap<Integer, ReportGenel>();
		ArrayList<String> diger = new ArrayList<String>();
		ArrayList<String> asilAlacak = new ArrayList<String>();
		ArrayList<String> temmerutFaizi = new ArrayList<String>();
		ArrayList<String> noterMasrafi = new ArrayList<String>();
		ArrayList<String> giderVergisi = new ArrayList<String>();
		ArrayList<String> toplam = new ArrayList<String>();
		ArrayList<String> gecikmeFaizi = new ArrayList<String>();
		ArrayList<String> faizGider = new ArrayList<String>();
		HashSet<Integer> idListe = getIDList(params, ID);
		ArrayList<String> ekler = new ArrayList<String>();
		if (idListe != null && idListe.size() > 0) {

			for (int id : idListe) {

				for (ReportGenel reportGenel : genelList) {

					if (id == reportGenel.getId()) {

						asilAlacak.add(reportGenel.getAsilAlacak1());
						reportGenel.setAsilAlacak(asilAlacak);

						temmerutFaizi.add(reportGenel.getTemmerutFaizi1());
						reportGenel.setTemmerutFaizi(temmerutFaizi);

						noterMasrafi.add(reportGenel.getNoterMasrafi1());
						reportGenel.setNoterMasrafi(noterMasrafi);

						giderVergisi.add(reportGenel.getGiderVergisi1());
						reportGenel.setGiderVergisi(giderVergisi);

						toplam.add(reportGenel.getToplam());
						reportGenel.setToplam1(toplam);

						gecikmeFaizi.add(reportGenel.getGecikmeFaizi1());
						reportGenel.setGecikmeFaizi(gecikmeFaizi);

						faizGider.add(reportGenel.getFaizGider1());
						reportGenel.setFaizGider(faizGider);

						diger.add(reportGenel.getDiger1());
						reportGenel.setDiger(diger);

						ekler.add(reportGenel.getTalepYaziEk());
						reportGenel.setEkler(ekler);

						int fistsize = trnListe.size();

						trnListe.put(id, reportGenel);

						int secondSize = trnListe.size();

						if (fistsize == secondSize) {
							trnListe.get(id).getAsilAlacak().add(reportGenel.getAsilAlacak1());
							trnListe.get(id).getNoterMasrafi().add(reportGenel.getNoterMasrafi1());
							trnListe.get(id).getGiderVergisi().add(reportGenel.getGiderVergisi1());
							trnListe.get(id).getGecikmeFaizi().add(reportGenel.getGecikmeFaizi1());
							trnListe.get(id).getTemmerutFaizi().add(reportGenel.getTemmerutFaizi1());
							trnListe.get(id).getToplam1().add(reportGenel.getToplam());
							trnListe.get(id).getFaizGider().add(reportGenel.getFaizGider1());
							trnListe.get(id).getDiger().add(reportGenel.getDiger1());
							trnListe.get(id).getEkler().add(reportGenel.getTalepYaziEk());
						}
					}

				}
			}

		}

		return trnListe;
	}

	// ID yi null gönderirsen tarihe göre alır, id yi dolu gönderirisen idye
	// göre alır.

	public ArrayList<ReportGenel> getPrintableList(SearchParams params, Integer ID) throws Exception {

		HashSet<Integer> idListe = getIDList(params, ID);
		HashMap<Integer, ReportGenel> trnListe = getTrnList(params, ID);
		// System.out.println(idListe.size() + " " + trnListe.size());
		ArrayList<ReportGenel> returnList = new ArrayList<ReportGenel>();
		@SuppressWarnings("unused")
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		if (trnListe.size() > 0 && idListe.size() > 0) {
			for (int id : idListe) {
				ReportGenel report = trnListe.get(id);
				//
				// faizGider
				//
				// notermasrafı

				if (report.getMuvekkilAdi().equals(ReportUtils.HSBC)) {
					if (report.getAsilAlacak().size() > 0 && report.getAsilAlacak() != null) {
						String hesapModel = "";
						double toplam1 = 0;

						int l1 = 0;
						int fark = 0;
						String a = "";
						for (int i = 0; i < report.getAsilAlacak().size(); i++) {
							l1 = report.getAsilAlacak().get(i).length();

							hesapModel = "\n";
							fark = 15 - l1;
							a = a + "Asıl Alacak";
							hesapModel = hesapModel + report.getAsilAlacak().get(i) + a + "\n";

							if (report.getFaizGider1() != "" && report.getFaizGider().size() > 0) {

								l1 = report.getFaizGider().get(i).length();
								fark = 15 - l1;
								a = StringUtils.leftPad(" ", fark, ' ');
								a = a + "İslenmis Akdi Faiz";
								hesapModel = hesapModel + report.getFaizGider().get(i) + a + "\n";

							}
							if (report.getGiderVergisi1() != "" && report.getGiderVergisi().size() > 0) {

								l1 = report.getGiderVergisi().get(i).length();
								fark = 15 - l1;
								a = StringUtils.leftPad(" ", fark, ' ');
								a = a + "Faizin %5 Gider Vergisi";

								hesapModel = hesapModel + report.getGiderVergisi().get(i) + a + "\n";

							}
							if (report.getTemmerutFaizi1() != "" && report.getTemmerutFaizi().size() > 0) {

								l1 = report.getTemmerutFaizi().get(i).length();
								fark = 15 - l1;
								a = StringUtils.leftPad(" ", fark, ' ');
								a = a + "İslenmis Temmerrut Faizi";
								hesapModel = hesapModel + report.getTemmerutFaizi().get(i) + a + "\n";

							}
							if (!report.getNoterMasrafi1().equals("$0.00")
									&& !report.getNoterMasrafi1().equals("0,00 TL")
									&& report.getNoterMasrafi().size() > 0) {

								l1 = report.getNoterMasrafi().get(i).length();
								fark = 15 - l1;
								a = StringUtils.leftPad(" ", fark, ' ');
								a = a + "Masraf";
								hesapModel = hesapModel + report.getNoterMasrafi().get(i) + a + "\n";

							}

							hesapModel = hesapModel + "----------------------------------------------------------- \n ";

							if (report.getHesap() == null || report.getHesap().equals("")) {
								report.setHesap(hesapModel);
							}

						}
						double t2 = 0;
						if (report.getToplam1().size() > 1) {
							String t = report.getToplam1().get(0);
							String b[] = t.split("");
							String ab = "";
							if (t.charAt(0) == 36) {
								for (int i1 = 1; i1 < b.length; i1++) {
									if (!b[i1].equals(String.valueOf(","))) {
										ab = ab + b[i1];
									}
									t2 = Double.valueOf(ab);
								}
							} else {
								int l = b.length;
								l = l - 2;
								for (int i1 = 0; i1 < l; i1++) {
									if (!b[i1].equals(String.valueOf(","))) {
										ab = ab + b[i1];
									}
									t2 = Double.valueOf(ab);
								}

							}
							String.valueOf(a);
							toplam1 = toplam1 + t2;

						}
						// if (report.getToplam1().size() > 1) {
						// report.setToplam(defaultFormat.format(toplam1));
						// String ek = report.getTalepYazisi();
						// String Ek1 = defaultFormat.format(toplam1) + " " +
						// ek;
						// report.setTalepYazisi(Ek1);
						// } else {
						// report.setTalepYazisi(report.getToplam() + " "
						// + report.getTalepYazisi());
						// }
						if (report.getHesap() == null) {
							report.setHesap(hesapModel);
						} else if (!report.getHesap().equals(hesapModel)) {
							String hes = report.getHesap();
							hes = hes + hesapModel;
							report.setHesap(hes);
						}
					}
				} else if (report.getMuvekkilAdi().equals(ReportUtils.AKBANK)) {
					if (report.getAsilAlacak().size() > 0 && report.getAsilAlacak() != null) {
						String hesapModel = "";
						report.setEk(report.getDiger1());
						report.setDiger1(ReportUtils.getAkbankHesapTitle(report.getDiger1()));
						
						double toplam1 = 0;

						int l1 = 0;
						int fark = 0;
						String a = "";
						for (int i = 0; i < report.getAsilAlacak().size(); i++) {
							l1 = report.getAsilAlacak().get(i).length();

							hesapModel = "\n";
							fark = 15 - l1;
							a = StringUtils.leftPad(" ", fark, ' ');
							a = a + "Asıl Alacak";
							hesapModel = hesapModel + report.getAsilAlacak().get(i) + a + "\n";

							if (report.getFaizGider1() != "" && report.getFaizGider().size() > 0) {

								l1 = report.getFaizGider().get(i).length();
								fark = 15 - l1;
								a = StringUtils.leftPad(" ", fark, ' ');
								a = a + "İslenmis Akdi Faiz";
								hesapModel = hesapModel + report.getFaizGider().get(i) + a + "\n";

							}
							if (report.getGiderVergisi1() != "" && report.getGiderVergisi().size() > 0) {

								l1 = report.getGiderVergisi().get(i).length();
								fark = 15 - l1;
								a = StringUtils.leftPad(" ", fark, ' ');
								a = a + "Faizin %5 Gider Vergisi";

								hesapModel = hesapModel + report.getGiderVergisi().get(i) + a + "\n";

							}
							if (report.getTemmerutFaizi1() != "" && report.getTemmerutFaizi().size() > 0) {

								l1 = report.getTemmerutFaizi().get(i).length();
								fark = 15 - l1;
								a = StringUtils.leftPad(" ", fark, ' ');
								a = a + "İslenmis Temmerrut Faizi";
								hesapModel = hesapModel + report.getTemmerutFaizi().get(i) + a + "\n";

							}
							if (!report.getNoterMasrafi1().equals("$0.00")
									&& !report.getNoterMasrafi1().equals("0,00 TL")
									&& report.getNoterMasrafi().size() > 0) {

								l1 = report.getNoterMasrafi().get(i).length();
								fark = 15 - l1;
								a = StringUtils.leftPad(" ", fark, ' ');
								a = a + "Masraf";
								hesapModel = hesapModel + report.getNoterMasrafi().get(i) + a + "\n";

							}

							hesapModel = hesapModel + "----------------------------------------------------------- \n ";

							if (report.getToplam() != null || report.getToplam() != "") {
								// fillAkbankList(report);
								hesapModel = hesapModel + report.getToplam() + " TOPLAM(*)";
								
							} else {
								
							}

							if (report.getHesap() == null || report.getHesap().equals("")) {
								report.setHesap(hesapModel);
								
							}

						}

						// if (report.getToplam1().size() > 1) {
						// report.setToplam(defaultFormat.format(toplam1));
						// String ek = report.getTalepYazisi();
						// String Ek1 = defaultFormat.format(toplam1) + " " +
						// ek;
						// report.setTalepYazisi(Ek1);
						// } else {
						// report.setTalepYazisi(report.getToplam() + " "
						// + report.getTalepYazisi());
						// }
						if (report.getHesap() == null) {
							report.setHesap(hesapModel);
						}
						// } else if (!report.getHesap().equals(hesapModel)) {
						// String hes = report.getHesap();
						// hes = hes + hesapModel;
						// report.setHesap(hes);
						// }
					}

				}

				// :TODO Yeni bankalar rapora eklendiğinde bu alan
				// güncellencek...

				if (report.getMuvekkilAdi().equals(ReportUtils.AKBANK)) {
					report.setHesapAkbank(fillAkbankList(report)); // Muhammet
																	// Abi bu
																	// method ne
																	// ise
																	// yariyo
																	// anlamadım.
				}

				returnList.add(report);
			}
			return returnList;

		} else {
			return null;
		}

	}

	private ArrayList<AkbankHesap> fillAkbankList(ReportGenel reportGenel) {
		ArrayList<AkbankHesap> hesapA = new ArrayList<AkbankHesap>();
		HashSet<AkbankHesap> akbankHesaps = new HashSet<AkbankHesap>();
		for (int i = 0; i < reportGenel.getAsilAlacak().size(); i++) {
			AkbankHesap hesap = new AkbankHesap();

			hesap.setAsilAlacak(reportGenel.getAsilAlacak().get(i));
			// :TODO borclu bilgi doldugunda güncellenecek...
			hesap.setBorc_bilgi("");
			hesap.setEkler(reportGenel.getEkler().get(i));
			hesap.setGiderVergisi(reportGenel.getGiderVergisi().get(i));
			Date date = new Date();
			hesap.setTarih(String.valueOf(date));
			hesap.setTemmerutFaizi(reportGenel.getTemmerutFaizi().get(i));
			hesap.setToplam1(reportGenel.getToplam1().get(i));
			
			akbankHesaps.add(hesap);

		}
		int count = 1;
		for (AkbankHesap hesap : akbankHesaps) {
			hesap.setBorclu_id(count);
			count++;
			hesapA.add(hesap);
		}
		return hesapA;
	}

	public ArrayList<ReportGenel> getAllTebligatFromIcraDosyaID(int id) throws Exception {
		ArrayList<ReportGenel> tzList = new ArrayList<ReportGenel>();
		SQL = "SELECT id, icra_dosyasi_no, borclu_adi, tc_no, adres, icra_bilgi, barkod"
				+ " FROM vwtebligat where id = " + id + ";";

		newConnectDB();

		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);

		while (rs.next()) {
			ReportGenel tz = new ReportGenel();
			String text = null;
			tz.setId(rs.getInt("id"));
			tz.setBorclu_id(rs.getInt("id"));
			tz.setIcraDosyaNo(rs.getString("icra_dosyasi_no"));
			text = rs.getString("borclu_adi") + " " + rs.getString("tc_no");
			tz.setBorclu(text);
			tz.setIcraBilgi(rs.getString("icra_bilgi"));
			tz.setBorcluAdres(rs.getString("adres"));
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			tz.setTarih(String.valueOf(dateFormat.format(date)));
			tz.setBarkot(rs.getString("barkod"));

			tzList.add(tz);

		}

		disconnectDB();

		checkAttributeForNull(tzList);

		return tzList;
	}

	public ArrayList<ReportGenel> getAllReportInfo() throws Exception {

		ArrayList<ReportGenel> genels = new ArrayList<ReportGenel>();

		SQL = "SELECT id, icra_dosyasi_no, muvekkil_adi, vergi_no, vergi_dairesi,"
				+ " borclu_adi, borclu_adres, talep_edilen_hak, takip_yolu, vekil_adi, asil_alacak, muvekkil_adres,  "
				+ "temerrut_faizi, faiz_gider_vergisi, noter_masrafi, icra_bilgi, gecikme_faizi, "
				+ "faiz_gider_v, icra_hesap, tapu_bilgi, borclu_tc, barkod, borclu_dogum, il  " + "FROM vwreport;";

		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);

		while (rs.next()) {
			ReportGenel rg = new ReportGenel();
			NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
			rg.setId(rs.getInt("id"));
			rg.setBorclu_id(rs.getInt("id"));
			rg.setIcraDosyaNo(rs.getString("icra_dosyasi_no"));
			rg.setAlacakliAdi(rs.getString("muvekkil_adi"));
			rg.setVergiNo(rs.getString("vergi_no"));
			rg.setVergiDairesi(rs.getString("vergi_dairesi"));
			rg.setBorclu(rs.getString("borclu_adi"));
			rg.setBorcluAdres(rs.getString("borclu_adres"));
			rg.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			rg.setTalepYazisi(rs.getString("talep_edilen_hak"));
			rg.setTakipYolu(rs.getString("takip_yolu"));
			rg.setVekil(rs.getString("vekil_adi"));
			rg.setAsilAlacak1(defaultFormat.format(rs.getDouble("asil_alacak")));
			rg.setTemmerutFaizi1(defaultFormat.format(rs.getDouble("temerrut_faizi")));
			rg.setGiderVergisi1(defaultFormat.format(rs.getDouble("faiz_gider_vergisi")));
			rg.setNoterMasrafi1(defaultFormat.format(rs.getDouble("noter_masrafi")));
			rg.setIcraBilgi(rs.getString("icra_bilgi"));
			rg.setGecikmeFaizi1(defaultFormat.format(rs.getDouble("gecikme_faizi")));
			rg.setBarkot(rs.getString("barkod"));

			genels.add(rg);

		}

		disconnectDB();
		checkAttributeForNull(genels);
		return genels;
	}

	public boolean saveGeneratedFileToDB(String fileName) throws Exception {

		DosyaEklemeDAO dao = new DosyaEklemeDAO();
		boolean a = dao.saveGeneratedFileToDB(fileName);
		return a;
	}

	public void checkAttributeForNull(ArrayList<ReportGenel> list) {

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getAlacakliAdi() == null) {
				list.get(i).setAlacakliAdi("");
			}
			if (list.get(i).getAsilAlacak1() == null) {
				list.get(i).setAsilAlacak1("");
			}
			if (list.get(i).getBorclu() == null) {
				list.get(i).setBorclu("");
			}
			if (list.get(i).getBorcluAdres() == null) {
				list.get(i).setBorcluAdres("");
			}
			if (list.get(i).getBorcluDogum() == null) {
				list.get(i).setBorcluDogum("");
			}
			if (list.get(i).getBorcluTC() == null) {
				list.get(i).setBorcluTC("");
			}
			if (list.get(i).getBorcMik() == null) {
				list.get(i).setBorcMik("");
			}
			if (list.get(i).getEk() == null) {
				list.get(i).setEk("");
			}
			if (list.get(i).getGecikmeFaizi() == null) {
				list.get(i).setGecikmeFaizi1("");
			}
			if (list.get(i).getGiderVergisi1() == "0") {
				list.get(i).setGiderVergisi1("");
			}
			if (list.get(i).getIcraBilgi() == null) {
				list.get(i).setIcraBilgi("");
			}
			if (list.get(i).getIcraDosyaNo() == null) {
				list.get(i).setIcraDosyaNo("");
			}
			if (list.get(i).getIcraMdHesapNo() == null) {
				list.get(i).setIcraMdHesapNo("");
			}
			if (list.get(i).getNoterMasrafi1() == "0") {
				list.get(i).setNoterMasrafi1("");
			}
			if (list.get(i).getTakipYolu() == null) {
				list.get(i).setTakipYolu("");
			}
			if (list.get(i).getTalepYazisi() == null) {
				list.get(i).setTalepYazisi("");
			}
			if (list.get(i).getTapuBilgi() == null) {
				list.get(i).setTapuBilgi("");
			}
			if (list.get(i).getTapuSicilMd() == null) {
				list.get(i).setTapuSicilMd("");
			}
			if (list.get(i).getTemmerutFaizi1() == "0") {
				list.get(i).setTemmerutFaizi1("");
			}
			if (list.get(i).getUser() == null) {
				list.get(i).setUser("");
			}
			if (list.get(i).getVekil() == null) {
				list.get(i).setVekil("");
			}
			if (list.get(i).getVergiDairesi() == null) {
				list.get(i).setVergiDairesi("");
			}
			if (list.get(i).getVergiNo() == null) {
				list.get(i).setVergiNo("");
			}
			if (list.get(i).getFaizGider1() == "0") {
				list.get(i).setFaizGider1("");
			}
			if (list.get(i).getFaizGider1() == null) {
				list.get(i).setFaizGider1("");
			}
			if (list.get(i).getBarkot() == null) {
				list.get(i).setBarkot("");
			}
			if (list.get(i).getKonu() == null) {
				list.get(i).setKonu("");
			}
			if (list.get(i).getHesap() == null) {
				list.get(i).setHesap("");
			}

		}

	}

	// MUHAMMET ALİ KAYA EKLEDİKLERİM BELLİ OLSUN DİYE BURAYA HAT ÇİZDİM SONRA
	// SİLİNECEK BU HAT
	// ----------------------------------------------------------------------------------------

	public ArrayList<ReportGenel> getFilteredReports(int ID) throws Exception {
		ArrayList<ReportGenel> genels = new ArrayList<ReportGenel>();

		SQL = "SELECT id, icra_dosyasi_no, eklemeT, muvekkil_adi, vergi_no, vergi_dairesi, muvekkil_adres, barkod,"
				+ " borclu_adi, borclu_adres, talep_edilen_hak, takip_yolu, vekil_adi, "
				+ "asil_alacak, temerrut_faizi, faiz_gider_vergisi, gecikme_faizi, "
				+ " noter_masrafi, faiz_gider_v, icra_bilgi, icra_hesap, tapu_bilgi, "
				+ "  borclu_tc, borclu_dogum  FROM vwreport where id=" + ID;

		newConnectDB();

		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		while (rs.next()) {
			String text = null;
			ReportGenel tt = new ReportGenel();
			NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
			tt.setId(rs.getInt("id"));
			tt.setBorclu_id(rs.getInt("id"));
			tt.setIcraDosyaNo(rs.getString("icra_dosyasi_no"));
			tt.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			text = rs.getString("muvekkil_adi") + " " + rs.getString("vergi_dairesi") + " " + rs.getString("vergi_no")
					+ " " + rs.getString("muvekkil_adres");
			tt.setAlacakliAdi(text);
			text = null;
			text = rs.getString("borclu_adi") + " "
					+ (rs.getString("borclu_adres") == null ? " " : rs.getString("borclu_adres"));
			tt.setBorclu(text);
			text = null;
			tt.setAsilAlacak1(defaultFormat.format(rs.getDouble("asil_alacak")));
			tt.setGiderVergisi1(defaultFormat.format(rs.getDouble("faiz_gider_vergisi")));
			// tt.setTakipAlacagi(defaultFormat.format(rs.getDouble("faiz_gider_vergisi")));
			tt.setTakipYolu(rs.getString("takip_yolu"));
			tt.setTalepYazisi(rs.getString("talep_edilen_hak"));
			tt.setFaizGider1(defaultFormat.format(rs.getDouble("gecikme_faizi")));
			tt.setNoterMasrafi1(defaultFormat.format(rs.getDouble("noter_masrafi")));
			tt.setTemmerutFaizi1(defaultFormat.format(rs.getDouble("temerrut_faizi")));
			tt.setVekil(rs.getString("vekil_adi"));
			tt.setIcraBilgi(rs.getString("icra_bilgi"));
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			tt.setTarih(String.valueOf(dateFormat.format(date)));
			Double toplam = rs.getDouble("asil_alacak") + rs.getDouble("noter_masrafi") + rs.getDouble("temerrut_faizi")
					+ rs.getDouble("gecikme_faizi") + rs.getDouble("faiz_gider_vergisi");
			tt.setGecikmeFaizi1(rs.getString("gecikme_faizi"));
			tt.setToplam(defaultFormat.format(toplam));
			String ek = "Borca Iliskin Ihtarname ve Eki Hesap Özeti".toString();
			tt.setBarkot(rs.getString("barkod"));
			tt.setEk(ek);

			genels.add(tt);
		}

		return genels;

	}

	public String configureEk(String ek) {
		@SuppressWarnings("unused")
		StringBuilder sb = new StringBuilder();

		String result = null;
		if (ek != null) {
			String[] a = ek.split("-TL");
			result = a[1];
		}

		return result;
	}

	public ArrayList<Model> getMuvekkilAdiComboboxModel() throws Exception {
		HashSet<String> values = new HashSet<String>();
		ArrayList<Model> returnList = new ArrayList<Model>();

		SQL = "SELECT muvekkil_adi  FROM vwreport; ";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		while (rs.next()) {
			values.add(rs.getString("muvekkil_adi"));
		}
		disconnectDB();
		for (String value : values) {
			Model model = new Model();
			model.setName(value);
			model.setValue(value);
			returnList.add(model);
		}

		return returnList;
	}

	public ArrayList<Model> getBorcluAdiComboboxModel() throws Exception {
		HashSet<String> values = new HashSet<String>();
		ArrayList<Model> returnList = new ArrayList<Model>();

		SQL = "SELECT borclu_adi  FROM vwreport; ";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		while (rs.next()) {
			values.add(rs.getString("borclu_adi"));
		}
		disconnectDB();
		for (String value : values) {
			Model model = new Model();
			model.setName(value);
			model.setValue(value);
			returnList.add(model);
		}

		return returnList;
	}

	public ArrayList<Model> getIcraDosyaNoComboboxModel() throws Exception {
		HashSet<String> values = new HashSet<String>();
		ArrayList<Model> returnList = new ArrayList<Model>();

		SQL = "SELECT icra_dosyasi_no  FROM vwreport; ";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		while (rs.next()) {
			values.add(rs.getString("icra_dosyasi_no"));
		}
		disconnectDB();
		for (String value : values) {
			Model model = new Model();
			model.setName(value);
			model.setValue(value);
			returnList.add(model);
		}

		return returnList;
	}

	public ArrayList<Model> getIcraMudurluguComboboxModel() throws Exception {
		HashSet<String> values = new HashSet<String>();
		ArrayList<Model> returnList = new ArrayList<Model>();

		SQL = "SELECT icra_bilgi  FROM vwreport; ";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		while (rs.next()) {
			values.add(rs.getString("icra_bilgi"));
		}
		disconnectDB();
		for (String value : values) {
			Model model = new Model();
			model.setName(value);
			model.setValue(value);
			returnList.add(model);
		}

		return returnList;
	}

}
