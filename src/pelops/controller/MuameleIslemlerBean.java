package pelops.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import pelops.dao.MuameleIslemleriDAO;
import pelops.model.MuameleIslemleri;

@ManagedBean(name = "muameleislemlerbean")
@SessionScoped
public class MuameleIslemlerBean {

	private Date Tarih, Tarih1, Tarih2;
	String borclu_adi;

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBorclu_adi() {
		return borclu_adi;
	}

	public void setBorclu_adi(String borclu_adi) {
		this.borclu_adi = borclu_adi;
	}

	public Date getTarih1() {
		return Tarih1;
	}

	public void setTarih1(Date tarih1) {
		Tarih1 = tarih1;
	}

	public Date getTarih2() {
		return Tarih2;
	}

	public void setTarih2(Date tarih2) {
		Tarih2 = tarih2;
	}

	public boolean isDuzenlesilrender() {
		return duzenlesilrender;
	}

	public void setDuzenlesilrender(boolean duzenlesilrender) {
		this.duzenlesilrender = duzenlesilrender;
	}

	boolean iptalrender, duzenlesilrender;

	public boolean isIptalrender() {
		return iptalrender;
	}

	public void setIptalrender(boolean iptalrender) {
		this.iptalrender = iptalrender;
	}

	public boolean isduzenlesilrender() {
		return duzenlesilrender;
	}

	public void setduzenlesilrender(boolean duzenlesilrender) {
		this.duzenlesilrender = duzenlesilrender;
	}

	public MuameleIslemlerBean() {
		
		
		status = 0;
		icra_dosya_no = AktifBean.getIcraDosyaNo();
		muameleIslm.setIcra_dosyasi_id(AktifBean.getIcraDosyaID());
		muameleIslm.setBorclu_adi(AktifBean.getBorcluAdi());
		this.setBorclu_adi(AktifBean.getBorcluAdi());
		muameleIslm.setBorclu_id(AktifBean.getBorcluId());
		this.setIptalrender(false);
		this.setduzenlesilrender(false);
		
		 
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public Date getTarih() {
		return Tarih;
	}

	public void setTarih(Date tarih) {
		Tarih = tarih;
	}

	public void kaydet() {

		muameleIslm = new MuameleIslemleri();
		icra_dosya_no = AktifBean.getIcraDosyaNo();
		muameleIslm.setIcra_dosyasi_id(AktifBean.getIcraDosyaID());
		muameleIslm.setBorclu_adi(AktifBean.getBorcluAdi());
		this.setBorclu_adi(AktifBean.getBorcluAdi());
		muameleIslm.setBorclu_id(AktifBean.getBorcluId());
		this.setIptalrender(true);
		this.setduzenlesilrender(true);

	}

	public void iptal() {
		this.setIptalrender(false);
		this.setduzenlesilrender(false);

	}

	public void duzenle() {

		this.setIptalrender(true);
		this.setduzenlesilrender(true);
		icra_dosya_no = AktifBean.getIcraDosyaNo();
	}

	ArrayList<MuameleIslemleri> muameleList = new ArrayList<MuameleIslemleri>();
	MuameleIslemleri muameleIslm = new MuameleIslemleri();
	String icra_dosya_no;

	public String getIcra_dosya_no() {
		return icra_dosya_no;
	}

	public void setIcra_dosya_no(String icra_dosya_no) {
		this.icra_dosya_no = icra_dosya_no;
	}

	public ArrayList<MuameleIslemleri> getMuameleList() {
		return muameleList;
	}

	public void setMuameleList(ArrayList<MuameleIslemleri> muameleList) {
		this.muameleList = muameleList;
	}

	public MuameleIslemleri getMuameleIslm() {
		return muameleIslm;
	}

	public void setMuameleIslm(MuameleIslemleri muameleIslm) {
		this.muameleIslm = muameleIslm;
	}

	public ArrayList<MuameleIslemleri> Liste() throws Exception {
		muameleList = new ArrayList<MuameleIslemleri>();
		MuameleIslemleriDAO muamele = new MuameleIslemleriDAO();
		muameleList = muamele.Liste();
		return muameleList;
	}

	public void save() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		if (muameleIslm.getTalep_ifadesi() == "" && muameleIslm.getMal_bilgisi() == ""
				&& muameleIslm.getMuhatap_adi() == "" && muameleIslm.getMuhatap_adresi() == ""
				&& muameleIslm.getTebligat_sonucu() == "" && muameleIslm.getMuamele_statusu() == "0"
				&& muameleIslm.getHaciz_sirasi() == 0 && muameleIslm.getHaciz_baslangic_tarihi() == null
				&& muameleIslm.getMaas_muvafakat() == "" && muameleIslm.getAciklama() == ""
				&& muameleIslm.getHaciz_miktari() == 0 && muameleIslm.getMuzekkere_adi() == "0") {

			context.addMessage(null, new FacesMessage("En az bir alan dolu olmalıdır!!!"));

		} else {

			if (status == 0) {

			
				MuameleIslemleriDAO muis = new MuameleIslemleriDAO();
				int result = muis.Kaydet(muameleIslm);

				if (result == 1) {

					context.addMessage(null, new FacesMessage("Kaydedildi!"));

				} else {
					context.addMessage(null, new FacesMessage("Kaydetme Başarısız!"));
				}

				muameleList = new ArrayList<MuameleIslemleri>();
				muameleList = muis.Liste();
				this.setIptalrender(false);
				this.setduzenlesilrender(false);

			} else {

				MuameleIslemleriDAO muis = new MuameleIslemleriDAO();
				int result = muis.Duzenle(muameleIslm);

				if (result == 1) {

					context.addMessage(null, new FacesMessage("Güncelleme Yapıldı!"));

				} else {
					context.addMessage(null, new FacesMessage("Güncelleme  Başarısız!"));
				}

				muameleList = new ArrayList<MuameleIslemleri>();
				muameleList = muis.Liste();
				status = 0;
				this.setIptalrender(false);
				this.setduzenlesilrender(false);

			}

		}

	}

	public void Sil() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		MuameleIslemleriDAO muis = new MuameleIslemleriDAO();
		int id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());
		int result = muis.Sil(id);

		if (result == 1) {

			context.addMessage(null, new FacesMessage("Silme  Yapıldı!"));

		} else {
			context.addMessage(null, new FacesMessage("Silme  Başarısız!"));
		}

		muameleList = new ArrayList<MuameleIslemleri>();
		muameleList = muis.Liste();
		status = 0;
	}

	public void Duzenle() throws Exception {

		status = 1;

		this.setIptalrender(false);
		this.setduzenlesilrender(false);

	}

	public void DuzenleAc() throws Exception {

		status = 1;

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("duzenle").toString());
		MuameleIslemleriDAO muis = new MuameleIslemleriDAO();
		muameleIslm = muis.Liste(id);
		this.setIptalrender(true);
		this.setduzenlesilrender(true);

	}

	public void dlgKaydet() throws Exception {
		save();
		RequestContext.getCurrentInstance().execute("PF('dlgMuamele').show()");
	}

	public void dlgVazgec() {
		iptal();
		RequestContext.getCurrentInstance().execute("PF('dlgMuamele').show()");
	}

	public void dlgPanelOpen() {
		duzenlesilrender = true;
		RequestContext.getCurrentInstance().execute("PF('dlgMuamele').show()");
	}

	public void dlgDuzenle() throws Exception {

		DuzenleAc();
		RequestContext.getCurrentInstance().execute("PF('dlgMuamele').show()");
	}

	public void dlgSil() throws Exception {
		Sil();
		RequestContext.getCurrentInstance().execute("PF('dlgMuamele').show()");
	}

	public void dlgYeniKayit() {
	
		kaydet();
		RequestContext.getCurrentInstance().execute("PF('dlgMuamele').show()");
	}

}
