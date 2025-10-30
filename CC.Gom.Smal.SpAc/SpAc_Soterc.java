package DDDTutorial_Modd.Gom;

import CC.COd.Finishd;

import CC.Encycloped.Abs.Scal.Physc.Physc_LMNt;
import CC.Encycloped.TIm.Tikbl;
import static CC.Encycloped.TIm.Tikbl.Tik_Ech;
import DDDTutorial_Modd.ConstL.StR;
import DDDTutorial_Modd.D3.*;
import CC.Encycloped.Abs.Scal.Gom.Trand_Inbl;
import CC.Encycloped.Abs.Scal.Physc.SIt.Drawbl_Atom;
import DDDTutorial_Modd.Voxl.Voxl_Grid;
import CC.Encycloped.Abs.Scal.Physc.*;
import java.util.*;
import java.util.List;
import static DDDTutorial_Modd.D3.GNrAtd_TerAn.SIz;
import static DDDTutorial_Modd.Util.Cast;
import static CC.Encycloped.Abs.Scal.Physc.Bordr_Stik.Stik_Chek;
import static CC.Encycloped.Abs.Scal.Physc.GraviT.GraviT_Sorc.Aply_GraviT;
import static MAn.LOc_Obstructn.Mov_Loop_MOd;
import static CC.Encycloped.Abs.Scal.Physc.Magnetsm.Magnetsm_SOrc.Aply_Magnetsm;
import static CC.Encycloped.Abs.Scal.Physc.Magnetsm.Magnetsm_SOrc.Magnetsm_SOrc_KE;
import static CC.Encycloped.Abs.Scal.Physc.Obstructn.LOc_Obstructr.LOc_Obstructr_KE;
import static CC.Encycloped.Abs.Scal.Physc.TLeportr.TLeport_Chek;
import static java.lang.Math.*;

import static java.lang.System.out;

public class 	   SpAc_Soterc
	 extends Physc_SpAc
{
	//<editor-fold desc="Drawbl">
	public List<Drawbl_Atom> Drawbl_Atomg=new ArrayList<>();
		public List<Drawbl_Atom>[] Drawblg_Sortd=new List[]{
			new ArrayList<>(),
			new ArrayList<>(),
			new ArrayList<>()};
		@Finishd(Is_Finishd=false)
		public List<? extends Drawbl_Atom> Get_Drawblg()
		{return Drawbl_Atomg;}
		@Finishd(Is_Finishd=false)
		public <Atom_Typ extends Drawbl_Atom>void Ad_Drawbl_Atom(Atom_Typ Atom)
		{
//			out.println("added "+Get_Drawblg().size()+"th drawbl");

			Drawbl_Atomg.add(Atom);
			for(Cam Cam:(List<Cam>)Get_Physc_LMNt_Cast("SEs"))
			{Cam.Ad_Drawbl(Atom);}
		}
		@Finishd(Is_Finishd=false)
		public void Rmov_Drawbl_Atom(D3_Drawbl_Atom Atom)
		{
			Drawbl_Atomg.remove(Atom);
			for(Cam Cam:(List<Cam>)Get_Physc_LMNt_Cast("SEs"))
			{Cam.Rmov_Drawbl(Atom);}
		}
	//</editor-fold>

	{
		Sortd_Objectg.put("Cub",new ArrayList<Cub>());
		Sortd_Objectg.put("Prism",new ArrayList<Prism>());
		Sortd_Objectg.put("Pyramid",new ArrayList<Pyramid>());
		Sortd_Objectg.put("StR",new ArrayList<StR>());
		Sortd_Objectg.put("Voxl_Grid",new ArrayList<Voxl_Grid>());
		Sortd_Objectg.put("GraviT_Efectbl",new ArrayList<Tik_Tran>());
	}

	//<editor-fold desc="Lit">
	public double[] Lit_Dr=new double[]{1,1,1};
	public double Sun_Pos=0;
	@Finishd(Is_Finishd=false)
	public void Control_Sun_Al_LIt()
	{
		Sun_Pos+=.005;
		double Map_SIz=GNrAtd_TerAn.Map_SIz*SIz;
		Lit_Dr[0]=Map_SIz/2-(Map_SIz/2+cos(Sun_Pos)*Map_SIz*10);
		Lit_Dr[1]=Map_SIz/2-(Map_SIz/2+sin(Sun_Pos)*Map_SIz*10);
		Lit_Dr[2]=-200;
	}
	//</editor-fold>
	@Override @Finishd(Is_Finishd=false)
	public void Tik()
	{
		Control_Sun_Al_LIt();
		super.Tik();
	}

	public double TIm=switch(2)
	{
		case 0->60;
		case 1->1024;
		case 2->1;
		default->throw new IllegalStateException("Unexpected value: "+1);
	};

	@Finishd(Is_Finishd=false)
	public SpAc_Soterc(Map<String,List<? extends Physc_LMNt>> Physcg,boolean Is_On)
	{
		Ad_Tik_Task(()->{
			out.println("");

			for(Tikbl Tikbl:Intrnl_Physcg)
			{Tikbl.Tik();}
			out.println("");

		});
		this.Physcg=Physcg;

		Ad_Tik_Task(()->{Mov_Loop_MOd(this);});

		Ad_Physc_Tikr(LOc_Obstructr_KE);

		Ad_Tik_Task(()->{Aply_GraviT(
			Cast(Get_Physc_LMNt("GraviT_Sorc")),
			Cast(Gar_Category("GraviT_Efectbl"))
		);});
		Ad_Physc_Tikr(Magnetsm_SOrc_KE);
		Ad_Tik_Task(()->{Aply_Magnetsm(
			Cast(Get_Physc_LMNt(Magnetsm_SOrc_KE)),
			Cast(Gar_Category("Magnetsm_Efectbl"))
		);});
		Ad_Physc_Tikr("Spring");
		Ad_Tik_Task(()->{Stik_Chek(Cast(Get_Physc_LMNt("Bordr_Stik")));});

		Ad_Tik_Task(()->{TLeport_Chek(Cast(Get_Physc_LMNt("TLeportr")),Get_Physc_LMNt_Cast("Tran"));});
//		Ad_Tik_Task(()->{SpEd_Limit_Loop(this);});
//		Ad_Physc_Tikr("Tran",()->{return TIm;});

		Ad_Tik_Task(()->{
			List<Trand_Inbl> Colisn=Gar_Category("Colisn");
//				out.println("Colisn_Object:"+Colisn.size());
			Tikbl.Tik_Ech(Colisn);
		});

		Set_Is_On(Is_On);
	}
		@Finishd(Is_Finishd=true)
		public SpAc_Soterc(boolean Is_On)
		{this(new HashMap<>(),Is_On);}
			@Finishd(Is_Finishd=true)
			public SpAc_Soterc()
			{this(true);}
}