public class NBody{
	public static double readRadius(String fileName){
		double R;
		In in=new In(fileName);
		in.readInt();
		R=in.readDouble();
		return R;
	}
	public static Planet[] readPlanets(String fileName){
		In in=new In(fileName);
		int n=in.readInt();
		Planet[] ap=new Planet[n];
		in.readDouble();
		for(int i=0;i<n;i++){
			ap[i]=new Planet(in.readDouble() , in.readDouble() ,in.readDouble() ,  in.readDouble() , in.readDouble(), in.readString());
		}
		return ap;
	}
	private static void drawBackGround(double R){
		String image="images/starfield.jpg";
		StdDraw.setScale(-R,R);
		StdDraw.clear();
		StdDraw.picture(0,0, image);
		StdDraw.show();
	}
	private static void DrawAllPlanets(Planet[] allp){
		for(Planet p: allp){
			p.draw();
		}
	}
	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double R=readRadius(filename);
		Planet[] allp=readPlanets(filename); 
		drawBackGround(R);
		DrawAllPlanets(allp);
		StdDraw.enableDoubleBuffering();
		for (double time=0 ; time<T ; time+=dt){
			double[] xForces=new double[allp.length];
			double[] yForces=new double[allp.length];
			int i=0;
			for(Planet p:allp){
				xForces[i]=p.calcNetForceExertedByX(allp);
				yForces[i]=p.calcNetForceExertedByY(allp);
				i+=1;
			}
			for(int n=0 ; n<i ; n+=1){
				allp[n].update(dt,xForces[n],yForces[n]);
			}
			drawBackGround(R);
			DrawAllPlanets(allp);
			StdDraw.show();
			StdDraw.pause(10);		
		}
		System.out.println(allp.length);
		System.out.println(R);
		for(int n=0;n<allp.length;n++){
			System.out.print(allp[n].xxPos+" ");
			System.out.print(allp[n].yyPos+" ");
			System.out.print(allp[n].xxVel+" ");
			System.out.print(allp[n].yyVel+" ");
			System.out.print(allp[n].mass+" ");
			System.out.println(allp[n].imgFileName);
		}
	}
}