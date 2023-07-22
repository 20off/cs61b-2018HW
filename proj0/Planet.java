public class Planet{
	private double G=6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP,double yP,double xV,double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;		
	}
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}
	public double calcDistance(Planet p){
		return Math.sqrt((p.xxPos-xxPos)*(p.xxPos-xxPos)+(p.yyPos-yyPos)*(p.yyPos-yyPos));
	}
	public double calcForceExertedBy(Planet p){
		return p.mass*G*mass/(this.calcDistance(p)*this.calcDistance(p));
	}
	public double calcForceExertedByX(Planet p){
		return this.calcForceExertedBy(p)*(p.xxPos-xxPos)/this.calcDistance(p);
	}
	public double calcForceExertedByY(Planet p){
		return this.calcForceExertedBy(p)*(p.yyPos-yyPos)/this.calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double sum=0;
		int i=0;
		while(i<allPlanets.length){
			if(this.equals(allPlanets[i])){
				i++;
				continue;
			}
			sum+=this.calcForceExertedByX(allPlanets[i]);
			i+=1;
		}
		return sum;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double sum=0;
		int i=0;
		while(i<allPlanets.length){
			if(this.equals(allPlanets[i])){
				i++;
				continue;
			}
			sum+=this.calcForceExertedByY(allPlanets[i]);
			i+=1;
		}
		return sum;
	}
	public void update(double t , double fx, double fy){
		double ax=fx/mass;
		double ay=fy/mass;
		xxVel+=ax*t;
		yyVel+=ay*t;
		xxPos+=xxVel*t;
		yyPos+=yyVel*t;
		
		
	}
	public void draw(){
		StdDraw.picture(xxPos,yyPos,imgFileName);
		StdDraw.show();
	}
	

}