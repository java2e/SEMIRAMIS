package pelops.controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pelops.dao.MuameleIslemleriDAO;
import pelops.db.DAO;
import pelops.model.Avukat;
import pelops.model.MuameleIslemleri;
import pelops.muameleislemleri.util.BankaModel;
import pelops.muameleislemleri.util.GayrimenkulModel;
import pelops.muameleislemleri.util.KurumModel;

public class TalepMuzekkereUtil {

	MuameleIslemleriDAO dao = new MuameleIslemleriDAO();

	public TalepMuzekkereUtil() {

	}

	public ArrayList<MuameleIslemleri> TalepMuzekkereListesiOlustur(MuameleIslemleri muamele,
			ArrayList<KurumModel> kurumList, ArrayList<BankaModel> bankaList,
			ArrayList<GayrimenkulModel> gayrimenkulList) {

		ArrayList<MuameleIslemleri> muameleList = new ArrayList<MuameleIslemleri>();
		MuameleIslemleri muameleIslemleri;

		// **********************Müzekereler***************************************************************

		// 103 Davetiye Müzekkeresi ( Araç )

		if (muamele.isDavetiyemuzekkeresi103arac()) {

			// İster Banka Bilgiler İster alacaklı bankalardan yapabiliriz
			// System.out.println(bankaList.get(0).getBankaBilgisi());

			// Müzekkere Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);

			muameleIslemleri.setMiktar(muamele.getDavetiyemuzekkeresi103Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Müzekkeresi(Araç)");
			muameleList.add(muameleIslemleri);

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getDavetiyemuzekkeresi103Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// 103 Davetiye Müzekkeresi ( SGK )

		if (muamele.isDavetiyemuzekkeresi103sgk()) {

			// Müzekkere Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getDavetiyemuzekkeresi103Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Müzekkeresi(SGK)");
			muameleList.add(muameleIslemleri);

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getDavetiyemuzekkeresi103Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// 103 Davetiye Müzekkeresi ( Gayrimenkul )

		if (muamele.isDavetiyemuzekkeresi103gayrimenkul()) {

			// Müzekkere Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getDavetiyemuzekkeresi103gayrimenkulSayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Müzekkeresi(Gayrimenkul)");
			muameleList.add(muameleIslemleri);

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getDavetiyemuzekkeresi103Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// 103 Davetiye Müzekkeresi ( Menkul )

		if (muamele.isDavetiyemuzekkeresi103menkul()) {

			// Müzekkere Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getDavetiyemuzekkeresi103menkulSayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Müzekkeresi(Menkul)");
			muameleList.add(muameleIslemleri);

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getDavetiyemuzekkeresi103Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// ******************************************************************************

		// Haciz İhbarnamesi Müzekkeresi ( 89/1 )

		if (muamele.isHacizihbarnamesimuzekkeresi891()) {

			StringBuilder builder = new StringBuilder();

			if (bankaList.size() > 0) {

				for (BankaModel bankaItem : bankaList) {

					muameleIslemleri = new MuameleIslemleri();
					muameleIslemleri = nesneDoldur(muamele);
					muameleIslemleri.setAlacakliBankasi(bankaItem.getAdi());
					muameleIslemleri.setMiktar(muamele.getHacizihbarnamesimuzekkeresi891Sayi());
					muameleIslemleri.setMuzekkereTalepAdi("Haciz İhbarnamesi Müzekkeresi(89/1)");
					muameleList.add(muameleIslemleri);

					builder.append(bankaItem.getAdi());
					builder.append(" ");

				}

				// ilgili Müzekkerenin Talebinin Toplu Bankalar için Oluşturma
				builder.trimToSize();

				// ilgili Müzekkerenin Talebini Oluşturma
				muameleIslemleri = new MuameleIslemleri();
				muameleIslemleri = nesneDoldur(muamele);
				muameleIslemleri.setAlacakliBankasi(builder.toString());
				muameleIslemleri.setMiktar(muamele.getHacizihbarnamesimuzekkeresi891Sayi());
				muameleIslemleri.setMuzekkereTalepAdi("Haciz İhbarnamesi Talebi(Bankalar İçin)");
				muameleList.add(muameleIslemleri);

			}

		}

		// *******************************************************************************

		// Adres Araştırma Müzekkeresi (Kurumlar İçin)

		if (muamele.isAdresarastirmamuzekkeresikurumlaricin()) {

			StringBuilder builder = new StringBuilder();

			// Her bir kurum için tek tek müzekkere ve toplu talep yapılır
			if (kurumList.size() > 0) {

				for (KurumModel kurumItem : kurumList) {

					muameleIslemleri = new MuameleIslemleri();
					muameleIslemleri = nesneDoldur(muamele);
					muameleIslemleri.setKurumAdi(kurumItem.getKurumAdi());
					muameleIslemleri.setMiktar(muamele.getAdresarastirmamuzekkeresikurumlaricinSayi());
					muameleIslemleri.setMuzekkereTalepAdi("Adres Araştırma Müzekkeresi(Kurumlar İçin)");
					muameleList.add(muameleIslemleri);
					builder.append(kurumItem.getKurumAdi());
					builder.append(" ");

				}

				// ilgili Müzekkerenin Talebinin Toplu Bankalar için Oluşturma
				builder.trimToSize();

				muameleIslemleri = new MuameleIslemleri();
				muameleIslemleri = nesneDoldur(muamele);
				muameleIslemleri.setKurumAdi(builder.toString());
				muameleIslemleri.setMiktar(muamele.getAdresarastirmamuzekkeresikurumlaricinSayi());
				muameleIslemleri.setMuzekkereTalepAdi("Adres Araştırma Talebi");
				muameleList.add(muameleIslemleri);

			}

		}

		// *******************************************************************************

		// Mevduat Haczi Müzekkeresi

		if (muamele.isMevduathaczimuzekkeresi()) {
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);

			muameleIslemleri.setMiktar(muamele.getMevduathaczimuzekkeresiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Mevduat Haczi Müzekkeresi");
			muameleList.add(muameleIslemleri);

			// ilgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMevduathaczimuzekkeresiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Mevduat Haczi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// *******************************************************************************

		// Maaş Haciz Müzekkeresi (Genel)

		if (muamele.isMaashacizmuzekkeresigenel()) {

			StringBuilder builder = new StringBuilder();
			
			if(muamele.getMuhatapAdi()=="" || muamele.getMuhatapAdresi()=="")
			{
				FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Maaş Haciz Müzekkeresi Genel Oluşturmak için MUHATTAB ADI VE ADRESİ EKLEMELİSİNİZ !!!"));
			RequestContext.getCurrentInstance().execute("PF('dlgMuhatap').show();");

			}
			else
			{

			if (bankaList.size() > 0) {

				for (BankaModel bankaItem : bankaList) {

					muameleIslemleri = new MuameleIslemleri();
					muameleIslemleri = nesneDoldur(muamele);
					muameleIslemleri.setAlacakliBankasi(bankaItem.getAdi());
					muameleIslemleri.setMiktar(muamele.getMaashacizmuzekkeresigenelSayi());
					muameleIslemleri.setMuzekkereTalepAdi("Maaş Haciz Müzekkeresi(Genel)");
					muameleList.add(muameleIslemleri);

					builder.append(bankaItem.getAdi());
					builder.append(" ");

				}

				// ilgili Müzekkerenin Talebinin Toplu Bankalar için Oluşturma
				builder.trimToSize();

				// ilgili Müzekkerenin Talebini Oluşturma
				muameleIslemleri = new MuameleIslemleri();
				muameleIslemleri = nesneDoldur(muamele);
				muameleIslemleri.setAlacakliBankasi(builder.toString());
				muameleIslemleri.setMiktar(muamele.getMaashacizmuzekkeresigenelSayi());
				muameleIslemleri.setMuzekkereTalepAdi("Maaş Haciz Talebi(Genel)");
				muameleList.add(muameleIslemleri);

			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						"Maaş Haciz Müzekkeresi Genel Oluşturmak için Alacaklı Banka Eklemelisiniz !!!"));
				
			}
			
			
			}
				
			
			

		}

		// *******************************************************************************

		// Maaş Haciz Müzekkeresi (Muvafakat)

		if (muamele.isMaashacizmuzekkeresimuvafakat()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMaashacizmuzekkeresimuvafakatSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Maaş Haciz Müzekkeresi(Muvafakat)");
			muameleList.add(muameleIslemleri);

			// ilgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMaashacizmuzekkeresimuvafakatSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Maaş Haciz Talebi(Muvafakat)");
			muameleList.add(muameleIslemleri);
		}

		// *******************************************************************************

		// PTT Haciz Müzekkeresi

		if (muamele.isPtthacizmuzekkeresi()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getPtthacizmuzekkeresiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("PTT Haciz Müzekkeresi");
			muameleList.add(muameleIslemleri);

			// ilgili Müzekkerenin Talebini Oluşturma
			// muameleIslemleri = new MuameleIslemleri();
			// muameleIslemleri = nesneDoldur(muamele);
			// muameleIslemleri.setMiktar(muamele.getPtthacizmuzekkeresiSayi());
			// muameleIslemleri.setMuzekkereTalepAdi("PTT Haciz Talebi");
			// muameleList.add(muameleIslemleri);
		}

		// *******************************************************************************

		// Tapu Haciz Müzekkeresi Nokta

		if (muamele.isTapuhacizmuzekkeresinokta()) {

			String il;
			String ilce;

			if (gayrimenkulList.size() > 0) {

				for (int i = 0; i < gayrimenkulList.size(); i++) {

					il = gayrimenkulList.get(i).getIl();
					ilce = gayrimenkulList.get(i).getIlce();

					for (int j = i + 1; j < gayrimenkulList.size(); j++) {

						if (gayrimenkulList.get(j).getIl() == il && gayrimenkulList.get(j).getIlce() == ilce) {

							gayrimenkulList.remove(j);

						}
					}

				}

				for (GayrimenkulModel gayrimenkulItem : gayrimenkulList) {

					StringBuilder builderForGayrimenkul = new StringBuilder();

					builderForGayrimenkul.append(gayrimenkulItem.getIl());
					builderForGayrimenkul.append(" ");
					builderForGayrimenkul.append(gayrimenkulItem.getIlce());
					builderForGayrimenkul.append(" ");
					builderForGayrimenkul.append(gayrimenkulItem.getAda());
					builderForGayrimenkul.append(" ");
					builderForGayrimenkul.append(gayrimenkulItem.getParsel());
					builderForGayrimenkul.append("/");
					builderForGayrimenkul.append(" "+gayrimenkulItem.getAciklama());

					muameleIslemleri = new MuameleIslemleri();
					muameleIslemleri = nesneDoldur(muamele);

					if (bankaList != null) {

						muameleIslemleri.setAlacakliBankasi(bankaList.get(0).getAdi());

					}
					
					muameleIslemleri.setTapuMudurluguIlIlce(gayrimenkulItem.getIlce()+" "+gayrimenkulItem.getIl());
					muameleIslemleri.setTapuMudurlugu(gayrimenkulItem.getIlce()+" TAPU SİCİL MÜDÜRLÜĞÜ");
					muameleIslemleri.setTapuIl(gayrimenkulItem.getIl());
					muameleIslemleri.setTapuIlce(gayrimenkulItem.getIlce());

					muameleIslemleri.setTapuKayitlari(builderForGayrimenkul.toString());
					muameleIslemleri.setMiktar(muamele.getTapuhacizmuzekkeresinoktaSayi());
					muameleIslemleri.setMuzekkereTalepAdi("Tapu Haciz Müzekkeresi(Nokta)");
					muameleIslemleri.setBankaAdi(muamele.getBankaAdi());
					muameleList.add(muameleIslemleri);

				}

			} else {

				FacesContext context = FacesContext.getCurrentInstance();
				
				RequestContext.getCurrentInstance().execute("PF('dlgGayrimenkul').show();");
				
				context.addMessage(null, new FacesMessage(
						"Tapu Haciz Müzekkeresi Oluşturmak için Mal Tipi  Eklemelisiniz !!!"));
			}

		}

		// *******************************************************************************

		// **********************Talepler***************************************************************

		// 103 Davetiye Talebi

		if (muamele.isDavetiyetalebi103()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getDavetiyemuzekkeresi103Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// **********************************************************************************************

		// Maaş Haciz Talebi Genel

		if (muamele.isMaashaciztalebigenel()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMenkulhaciztalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Maaş Haciz Talebi(Genel)");
			muameleList.add(muameleIslemleri);

		}

		// **********************************************************************************************

		// Maaş Haciz Talebi Muvafakat

		if (muamele.isMaashacizmuzekkeresimuvafakat()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMaashacizmuzekkeresimuvafakatSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Maaş Haciz Talebi(Muvafakat)");
			muameleList.add(muameleIslemleri);

		}

		// **********************************************************************************************

		// PTT Haciz Talebi

		if (muamele.isPtthaciztalebi()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getPtthaciztalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("PTT Haciz Talebi");
			muameleList.add(muameleIslemleri);

		}

		// **********************************************************************************************

		// Araç Şerhi Talebi

		// if (muamele.isAracserhitalebi()) {
		//
		// // İlgili Müzekkerenin Talebini Oluşturma
		// muameleIslemleri = new MuameleIslemleri();
		// muameleIslemleri = nesneDoldur(muamele);
		// muameleIslemleri.setMiktar(muamele.getAracserhitalebiSayi());
		// muameleIslemleri.setMuzekkereTalepAdi("Araç Şerhi Talebi");
		// muameleList.add(muameleIslemleri);
		//
		// }

		// **********************************************************************************************
		// Adres Araştırma Talebi

		if (muamele.isAdresarastimatalebi()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getAdresarastimatalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Adres Araştırma Talebi");
			muameleList.add(muameleIslemleri);

		}

		// **********************************************************************************************

		// Araç Haczi Talebi

		if (muamele.isArachaczitalebi()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getArachaczitalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Araç Haczi Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************
		// Araç Yakalama Talebi

		if (muamele.isAracyakalamatalebi()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getAracyakalamatalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Araç Yakalama Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Dosya İşlemden Kaldırılma Talebi(Harç Borçluda)

		if (muamele.isDosyaislemdenkaldirilmatalebiharcborcluda()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getDosyaislemdenkaldirilmatalebiharcborcludaSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Dosya İşlemden Kaldırılma Talebi(Harç Borçluda)");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Dosya İşlemden Kaldırılma Talebi(Harç Büroda)

		if (muamele.isDosyaislemdenkaldirilmatalebiharcburoda()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getDosyaislemdenkaldirilmatalebiharcburodaSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Dosya İşlemden Kaldırılma Talebi(Harç Büroda)");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Feragat Talebi

		if (muamele.isFeragattalebi()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getFeragattalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Feragat Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Aile Kayıt Tablosu Talebi
		if (muamele.isAilekayittablosutalebi()) {

			// İlgili Müzekkerenin Talebini Oluşturma
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getAilekayittablosutalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Aile Kayıt Tablosu Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Tapu Haciz Talebi
		// if (muamele.isAilekayittablosutalebi()) {
		//
		// // İlgili Müzekkerenin Talebini Oluşturma
		// muameleIslemleri = new MuameleIslemleri();
		// muameleIslemleri = nesneDoldur(muamele);
		// muameleIslemleri.setMiktar(muamele.getAilekayittablosutalebiSayi());
		// muameleIslemleri.setMuzekkereTalepAdi("Aile Kayıt Tablosu Talebi");
		// muameleList.add(muameleIslemleri);
		// }

		// **********************************************************************************************

		// Haciz İhbarname Talebi

		if (muamele.isHacizihbarnamesitalebibankalaricin()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getHacizihbarnamesimuzekkeresi891Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("Haciz İhbarnamesi Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Kapanış Harç Borçluda Talebi

		if (muamele.isKapanistalebiharcborcludatalebi()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getKapanistalebiharcborcludatalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Kapanış Harç Borçluda Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Kapanış Harç Büroda Talebi

		if (muamele.isKapanistalebiharcburodatalebi()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getKapanistalebiharcburodatalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Kapanış Harç Büroda Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Menkul Haciz Talebi

		if (muamele.isMenkulhaciztalebi()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMenkulhaciztalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Menkul Haciz Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Mernis Adresi Ödeme Emri Talebi

		if (muamele.isMernisadresineodemeemritalebi()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMernisadresineodemeemritalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Mernis Adresi Ödeme Emri Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Mevduat Haczi Talebi

		if (muamele.isMevduathaczitalebi()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMenkulhaciztalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Mevduat Haczi Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// SGK Adresi Ödeme Emri Talebi

		// if (muamele.isSgkadresiodemeemritalebi()) {
		//
		// muameleIslemleri = new MuameleIslemleri();
		// muameleIslemleri = nesneDoldur(muamele);
		// muameleIslemleri.setMiktar(muamele.getSgkadresiodemeemritalebiSayi());
		// muameleIslemleri.setMuzekkereTalepAdi("SGK Adresi Ödeme Emri
		// Talebi");
		// muameleList.add(muameleIslemleri);
		// }

		// **********************************************************************************************

		// Ticaret Sicil Adresi Sorma Talebi

		if (muamele.isTicaretsiciladressormatalebi()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTicaretsiciladressormatalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Ticaret Sicil Adresi Sorma Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// TK/21 Talebi
		if (muamele.isTk21talebi()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTk21talebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("TK/21 Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Yenileme Talebi
		if (muamele.isYenilemetalebi()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getYenilemetalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Yenileme Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Yurtiçi Adresi Ödeme Emri Talebi
		if (muamele.isYurticiadresiodemeemritalebi()) {

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getYurticiadresiodemeemritalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("Yurtiçi Adresi Ödeme Emri Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Çoklu 4lu 5 Banka Talebi

		if (muamele.isCoklu4lu5bankatalebi()) {

			// 4lu sorgu doldurulacak

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getCoklu4lu5bankatalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("4lu( 5 Banka ) Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		// Çoklu 4lu 7 Banka Talebi

		if (muamele.isCoklu4lu7bankatalebi()) {

			// 7li sorgu doldurulacak

			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getCoklu4lu7bankatalebiSayi());
			muameleIslemleri.setMuzekkereTalepAdi("4lu( 7 Banka ) Talebi");
			muameleList.add(muameleIslemleri);
		}

		// **********************************************************************************************

		return muameleList;

	}

	public MuameleIslemleri nesneDoldur(MuameleIslemleri muamele) {

		MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
		muameleIslemleri.setBaslik(muamele.getBaslik());
		muameleIslemleri.setParagraf1(muamele.getParagraf1());
		muameleIslemleri.setParagraf2(muamele.getParagraf2());
		muameleIslemleri.setBorcluAdi(muamele.getBorcluAdi());
		muameleIslemleri.setTarih(muamele.getTarih());
		muameleIslemleri.setIcraDosyaNo(muamele.getIcraDosyaNo());
		muameleIslemleri.setAciklama(muamele.getAciklama());
		muameleIslemleri.setAlacakFaizTutari(muamele.getAlacakFaizTutari());
		muameleIslemleri.setAlacakliBankasi(muamele.getAlacakliBankasi());
		muameleIslemleri.setAlacakliMail(muamele.getAlacakliMail());
		muameleIslemleri.setAlacakliTel(muamele.getAlacakliTel());
		muameleIslemleri.setAvukatId(muamele.getAvukatId());
		muameleIslemleri.setBankaBilgileri(muamele.getBankaBilgileri());
		muameleIslemleri.setBarcode("123456789012");
		muameleIslemleri.setBorcluAdresi(muamele.getBorcluAdresi());
		muameleIslemleri.setBorcluTc(muamele.getBorcluTc());
		muameleIslemleri.setHacizBaslangicTarihi(muamele.getHacizBaslangicTarihi());
		muameleIslemleri.setHacizMiktari(muamele.getHacizMiktari());
		muameleIslemleri.setHacizSirasi(muamele.getHacizSirasi());
		muameleIslemleri.setHazirlayanId(muamele.getHazirlayanId());
		muameleIslemleri.setIcraDosyaId(muamele.getIcraDosyaId());
		muameleIslemleri.setIcraMudurluguId(muamele.getIcraMudurluguId());
		muameleIslemleri.setMaasMuvafakat(muamele.getMaasMuvafakat());
		muameleIslemleri.setMalBilgisi(muamele.getMalBilgisi());
		muameleIslemleri.setMalTipiId(muamele.getMalTipiId());
		muameleIslemleri.setMasrafMiktari(muamele.getMasrafMiktari());
		muameleIslemleri.setMasrafTipiId(muamele.getMasrafTipiId());
		muameleIslemleri.setMuameleStatusuId(muamele.getMuameleStatusuId());
		muameleIslemleri.setMuameleTarihi(muamele.getMuameleTarihi());
		muameleIslemleri.setMuhatapAdi(muamele.getMuhatapAdi());
		muameleIslemleri.setMuhatapAdresi(muamele.getMuhatapAdresi());
		muameleIslemleri.setPersonelId(muamele.getPersonelId());
		muameleIslemleri.setSemiramisNo(muamele.getSemiramisNo());
		muameleIslemleri.setTalepIfadesi(muamele.getTalepIfadesi());
		muameleIslemleri.setTebligatTarihi(muamele.getTebligatTarihi());
		muameleIslemleri.setTebligatSonucu(muamele.getTebligatSonucu());
		muameleIslemleri.setIcraMudurluguAdi(muamele.getIcraMudurluguAdi());
		muameleIslemleri.setHazirlayanAdi(muamele.getHazirlayanAdi());
		muameleIslemleri.setAvukatAdi(muamele.getAvukatAdi());
		muameleIslemleri.setAlacakliAdi(muamele.getAlacakliAdi());
		muameleIslemleri.setDogumTarihi(muamele.getDogumTarihi());

		return muameleIslemleri;
	}

	public boolean isMuzekkere(String muzekkereTalep) {

		boolean result = false;

		switch (muzekkereTalep) {

		case "103 Davetiyesi Müzekkeresi(Araç)":

			result = true;
			break;

		case "Adres Araştırma Müzekkeresi(Kurumlar İçin)":
			result = true;

		case "103 Davetiyesi Müzekkeresi(SGK)":

			result = true;
			break;

		case "103 Davetiyesi Müzekkeresi(Gayrimenkul)":

			result = true;
			break;

		case "103 Davetiyesi Müzekkeresi(Menkul)":

			result = true;
			break;

		case "Haciz İhbarnamesi Müzekkeresi(Bankalar İçin)":

			result = true;
			break;

		case "Maaş Haciz Müzekkeresi(Muvafakat)":

			result = true;
			break;

		case "Maaş Haciz Müzekkeresi(Genel)":

			result = true;
			break;

		case "PTT Haciz Müzekkeresi":

			result = true;
			break;

		case "Tapu Haciz Müzekkeresi(Nokta)":

			result = true;
			break;

		case "Mevduat Haczi Müzekkeresi":

			result = true;
			break;

		default:
			break;
		}

		return result;
	}

	public java.util.Date getCurrentDate() {

		String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = null;
		try {
			date = (java.util.Date) formatter.parse(currentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;

	}

	public String getIcraMudurluguText(int icraMudurluguId) throws Exception {

		String icraMudurluguText = dao.getIcraMudurluguText(icraMudurluguId);

		return icraMudurluguText;

	}

	public String getRiskYoneticisiText(int riskYoneticisiId) throws Exception {

		String riskYoneticisiText = dao.getRiskYoneticisiText(riskYoneticisiId);

		return riskYoneticisiText;
	}

	public String getAlacakliEpostaText(int alacakliId) throws Exception {

		String alacakliEpostaText = dao.getAlacakliEpostaText(alacakliId);

		return alacakliEpostaText;
	}

	public String getIcraDosyaNoText(int icraDosyaId) throws Exception {

		String icraDosyaNoText = dao.getIcraDosyaNoText(icraDosyaId);

		return icraDosyaNoText;
	}

	public String getBorcluAdresiText(int borcluId) throws Exception {

		String borcluAdresiText = dao.getBorcluAdresiText(borcluId);

		return borcluAdresiText;

	}

	public String getBuroAdresiText(int icraDosyaId) throws Exception {

		String buroAdresiText = dao.getBuroAdresiText(icraDosyaId);

		return buroAdresiText;

	}

	public String getAlacakliTelText(int icraDosyaId) throws Exception {

		String alacakliTelText = dao.getAlacakliTelText(icraDosyaId);

		return alacakliTelText;

	}

	public String getBorcluTcText(int borclubilgisiID) throws Exception {

		String borcluTcText = dao.getBorcluTcText(borclubilgisiID);

		return borcluTcText;

	}

}
