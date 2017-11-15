package io.github.bi0qaw.biosphere.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class TrigLib {

    public static Number[] llen(Location[] l){
    	Number[] nums = new Number[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		nums[i] = tempLoc.clone().toVector().length();
    		i++;
    	}
    	return nums;
    }

    public static Location[] lneg(Location[] l){
    	Location[] locs = new Location[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().multiply(-1);
    		i++;
    	}
       return locs;
    }

    public static Location[] offset(Location[] l, Location l2){
    	Location[] locs = new Location[Array.getLength(l)];
    	Vector vect = l2.clone().toVector();
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().add(vect);
    		i++;
    	}
        return locs;
    }

    public static Location[] coffset(Location[] l, double x, double y, double z){
    	Location[] locs = new Location[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().add(x, y, z);
    		i++;
    	}
        return locs;
    }

    public static Location[] vectorOffset(Location[] locations, Vector[] vectors) {
    	Location[] offset = new Location[locations.length * vectors.length];
		int i = 0;
    	for (Location l: locations) {
    		for (Vector v: vectors) {
    			offset[i] = l.clone().add(v);
    			i++;
			}
		}
		return offset;

	}

    public static Location[] mult(Location[] l, double p){
    	Location[] locs = new Location[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().multiply(p);
    		i++;
    	}
        return locs;
    }
    public static Location[] lmult(Location[] l, Location l2){
    	Location[] locs = new Location[Array.getLength(l)];
    	Vector vect = l2.clone().toVector();
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().toVector().multiply(vect).toLocation(tempLoc.getWorld());
    		i++;
    	}
    	return locs;
    }

    public static Location[] lnorm(Location[] l){
    	Location[] locs = new Location[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().toVector().normalize().toLocation(tempLoc.getWorld());
    		i++;
    	}
        return locs;
    }

    public static Location[] relloc(Location[] l, Location l2){
    	Location[] locs = new Location[Array.getLength(l)];
    	Vector vect = l2.clone().toVector();
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().subtract(vect);
    		i++;
    	}
    	return locs;
    }

    public static Number[] dotp(Location[] l, Location l2){
    	Number[] nums = new Number[Array.getLength(l)];
    	Vector vect = l2.clone().toVector();
    	int i = 0;
    	for(Location tempLoc : l){
    		nums[i] = tempLoc.clone().toVector().dot(vect);
    		i++;
    	}
        return nums;
    }

    public static Number[] cdotp(Location[] l, double x, double y, double z){
    	Number[] nums = new Number[Array.getLength(l)];
    	Vector vect = new Vector(x, y, z);
    	int i = 0;
    	for(Location tempLoc : l){
    		nums[i] = tempLoc.clone().toVector().dot(vect);
    		i++;
    	}
        return nums;
    }

    public static Location[] crossp(Location[] l, Location l2){
    	Location[] locs = new Location[Array.getLength(l)];
    	Vector vect = l2.clone().toVector();
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().toVector().crossProduct(vect).toLocation(tempLoc.getWorld());
    		i++;
    	}
    	return locs;
    }

    public static Location[] ccrossp(Location[] l, double x, double y, double z){
    	Location[] locs = new Location[Array.getLength(l)];
    	Vector vect = new Vector(x, y, z);
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().toVector().crossProduct(vect).toLocation(tempLoc.getWorld());
    		i++;
    	}
    	return locs;
    }

    public static Location[] rot(Location[] l, Location center, Vector axis, double phi){
    	Vector centerVect = center.clone().toVector();
    	Location[] locs = new Location[Array.getLength(l)];
    	double n1 = axis.getX();
        double n2 = axis.getY();
        double n3 = axis.getZ();
        double x;
        double y;
        double z;
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().subtract(centerVect);
    		x = locs[i].getX();
            y = locs[i].getY();
            z = locs[i].getZ();
    		locs[i].setX(x * (Math.pow(n1, 2) * ( 1 - Math.cos(phi)) + Math.cos(phi)) + y * (n2 * n1 * ( 1 - Math.cos(phi)) - n3 * Math.sin(phi)) + z * (n1 * n3 * ( 1-Math.cos(phi)) + n2 * (Math.sin(phi))) );
    	    locs[i].setY(x * (n1 * n2 * ( 1 - Math.cos(phi)) + n3 * Math.sin(phi)) + y * (Math.pow(n2, 2) * (1-Math.cos(phi))+Math.cos(phi)+z*(n2*n3*(1-Math.cos(phi))-n1*Math.sin(phi))));
    	    locs[i].setZ(x * (n3*n1* (1-Math.cos(phi))-n2*Math.sin(phi))+y*(n3*n2* ( 1 - Math.cos(phi))+n1*Math.sin(phi))+ z* (Math.pow(n3,2) * (1-Math.cos(phi)) + Math.cos(phi)));
    	    i++;
    	}
    	return offset(locs,center);
    }

    public static Location[] xrot(Location[] l, Location center, double phi){
    	Vector centerVect = center.clone().toVector();
    	Location[] locs = new Location[Array.getLength(l)];
    	double y;
    	double z;
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().subtract(centerVect);
    		y = locs[i].getY();
    		z = locs[i].getZ();
    		locs[i].setY(y * Math.cos(phi) + z * Math.sin(phi));
            locs[i].setZ(-y * Math.sin(phi) + z * Math.cos(phi));	
            i++;
    	}
        return offset(locs,center);
    }

    public static Location[] yrot(Location[] l, Location center, double phi){
    	Vector centerVect = center.clone().toVector();
    	Location[] locs = new Location[Array.getLength(l)];
    	double x;
    	double z;
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().subtract(centerVect);
    		x = locs[i].getX();
    		z = locs[i].getZ();
    		locs[i].setX(x * Math.cos(phi) - z * Math.sin(phi));
            locs[i].setZ(x * Math.sin(phi) + z * Math.cos(phi));
            locs[i].add(center);
            i++;
    	}
        return locs;
    }
    
    public static Location[] zrot(Location[] l, Location center, double phi){
    	Vector centerVect = center.clone().toVector();
    	Location[] locs = new Location[Array.getLength(l)];
    	double x;
    	double y;
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().subtract(centerVect);
    		x = locs[i].getX();
    		y = locs[i].getY();
    		locs[i].setX(x * Math.cos(phi) + y * Math.sin(phi));
            locs[i].setY(-x * Math.sin(phi) + y * Math.cos(phi));
            i++;
    	}
        return offset(locs,center);
    }

    public static Location[] ptrefl(Location[] l, Location l2){
    	Vector vect = l2.clone().toVector();
    	Location[] locs = new Location[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().subtract(vect).multiply(-1).add(vect);
    		i++;
    	}
    	return locs;
    }

    public static Location[] refl(Location[] l, Location point, Vector dir){
    	Location[] locs = new Location[Array.getLength(l)];
    	Vector vect = point.clone().toVector();
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.toVector().subtract(vect).multiply(-1).multiply(dir).add(vect).toLocation(tempLoc.getWorld());
    		i++;
    	}
    	return locs;		
    }

    public static Location[] scale(Location[] l, Location center, double d){
    	Vector centerVect = center.clone().toVector();
    	Location[] locs = new Location[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().subtract(centerVect).multiply(d).add(centerVect);
    		i++;
    	}
    	return locs;
    }
    
    public static Location[] dirscale(Location[] l, Location center, Vector dir, double d){
    	Location[] locs = new Location[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().subtract(center).toVector().multiply(d).multiply(dir).toLocation(tempLoc.getWorld()).add(center);
    		i++;
    	}
    	return locs;			
    }
    
    public static Location[] cdirscale(Location[] l, Location center, double x, double y, double z){
    	Vector centerVect = center.clone().toVector();
    	Location[] locs = new Location[Array.getLength(l)];
    	Vector vect = new Vector(x, y ,z);
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().subtract(centerVect).toVector().multiply(vect).toLocation(tempLoc.getWorld()).add(centerVect);
    		i++;
    	}
    	return locs;
    }
    
    public static Location[] midpt(Location[] l){
    	Location loc = new Location(l[0].getWorld(), 0, 0, 0);
    	for(Location tempLoc : l){
    		loc.add(tempLoc);
    	}
    	return new Location[]{loc.multiply(1D / Array.getLength(l))};
    }
    
    public static Location[] sphloc(Location[] l, double phi, double the, double r){
    	Location[] locs = new Location[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone();
    		locs[i].setX(tempLoc.getX() + r * Math.sin(the) * Math.cos(phi));
        	locs[i].setY(tempLoc.getY() + r * Math.cos(the));
        	locs[i].setZ(tempLoc.getZ() + r * Math.sin(the) * Math.sin(phi));
        	i++;
    	}
    	return locs;
    }
    
    public static Location[] cylloc(Location[] l, double phi, double r, double h){
    	Location[] locs = new Location[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone();
    		locs[i].setX(tempLoc.getX() + r * Math.cos(phi));
        	locs[i].setY(tempLoc.getY() + h);
        	locs[i].setZ(tempLoc.getZ() + r * Math.sin(phi));
        	i++;
    	}
    	return locs;
    }
    
    public static Location[] cubeloc(Location[] l, Location l2, double r){
    	Location[] locs = new Location[Array.getLength(l)];
    	double d = r / Math.pow(2,  0.5);
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone();
    		locs[i].setX(tempLoc.getX() + d * (2 * l2.getX() - 1));
        	locs[i].setY(tempLoc.getY() + d * (2 * l2.getY() - 1));
        	locs[i].setZ(tempLoc.getZ() + d * (2 * l2.getZ() - 1));
        	i++;
    	}
    	return locs;
    }
    
    public static Location[] ccubeloc(Location[] l, double x, double y, double z, double r){
    	Location[] locs = new Location[Array.getLength(l)];
    	double d = r / Math.pow(2,  0.5);
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone();
    		locs[i].setX(tempLoc.getX() + d * (2 * x - 1));
        	locs[i].setY(tempLoc.getY() + d * (2 * y - 1));
        	locs[i].setZ(tempLoc.getZ() + d * (2 * z - 1));
        	i++;
    	}   	
    	return locs;
    }
    
    public static Location[] lineloc(Location[] l, Location l2, double p){
    	Vector vect = l2.clone().toVector();
    	Location[] locs = new Location[Array.getLength(l)];
    	int i = 0;
    	for(Location tempLoc : l){
    		locs[i] = tempLoc.clone().add(vect.clone().subtract(tempLoc.clone().toVector()).multiply(p));
    		i++;
    	}
    	return locs;
    }
    
    public static Location[] getLine(Location[] l, Location l2, double d){
    	Vector vect = l2.clone().toVector();
    	List<Location> locs = new ArrayList<Location>();
    	Vector v;
    	Vector tempVect;
    	
    	int k;
    	int i;
    	for(Location tempLoc : l){
    		tempVect = tempLoc.toVector();
    		k  = (int) (tempVect.distance(vect) * d);
    		v = vect.clone().subtract(tempVect).multiply(1D/k);
    		for(i = 0; i < k ; i++){
    			locs.add(tempLoc.clone().add(v.clone().multiply(i)));
    		}
    	}
    	return (Location[]) locs.toArray(new Location[locs.size()]);
    }
   
    public static Location[] linkAll(Location[] loc, double d){
    	List<Location> locs = new ArrayList<Location>();
    	for(Location l1 : loc){
    		for(Location l2 : loc){
    			if(!l1.equals(l2)){
    				locs.addAll(Arrays.asList(getLine(new Location[]{l1}, l2, d)));
    			}
    		}
    	}
    	return locs.toArray(new Location[locs.size()]);
    }
    
    public static Location[] getPoly(Location[] l, int n, double r){
    	double phi = Math.PI * 2 / (double) n;
    	Location[] locs = new Location[n*Array.getLength(l)];
    	Location loc;
    	int i = 0;
    	int j = 0;
    	for(Location tempLoc : l){
    		loc = tempLoc.clone().add(r, 0D, 0D);
    		for(i = 0; i < n; i++){
    			locs[j] = yrot(new Location[]{loc}, tempLoc, i * phi)[0];
    			j++;
    		}
    	}
    	return locs;
    }
    
    public static Location[] getPolyOutline(Location[] l, Integer n, double r, double d){
    	Location[] loc = new Location[n];
    	List<Location> locs = new ArrayList<Location>();
    	int i = 0;
    	for(Location tempLoc : l){
    		loc = getPoly(new Location[]{tempLoc}, n , r);
    		for(i = 0; i < n - 1; i++){
    			locs.addAll(Arrays.asList(getLine(new Location[]{loc[i]},loc[i+1],d)));
    		}
    		locs.addAll(Arrays.asList(getLine(new Location[]{loc[i]},loc[0],d)));
    	}
    	return (Location[]) locs.toArray(new Location[locs.size()]);
    }
    
    public static Location[] getHelix(Location[] l, double r, double h, double k, double d){
    	int n = (int) ( h * 2 * Math.PI * r * d);
    	double dphi = 1D / (k * r * d);
    	Location[] locs = new Location[n * Array.getLength(l)];
    	int i = 0;
    	int j = 0;
    	for(Location tempLoc : l){
    		for(j = 0; j < n; j++){
    			locs[i] = cylloc(new Location[]{ tempLoc }, j * dphi, r, j * h / n)[0];
    			i++;
    		}
    	}
    	return locs;
    	
    }
    public static Location[] getCube(Location[] l, double r){
    	return vectorOffset(l, VectorLib.getCube(r));
    }
    
    public static Location[] getCubeOutline(Location[] l, double r, double d){
    	return vectorOffset(l, VectorLib.getCubeOutline(r, d));
    }
    
    public static Location[] getSphereRand(Location[] l, double r, double d){
    	List<Location> locs = new ArrayList<Location>();
    	int n = (int) (4 * Math.PI * r * r * d);
    	double phi = 0D;
    	double the = 0D;
    	Random randGen = new Random();
    	for(Location tempLoc : l){
    		for(int i = 0; i < n; i++){
    			phi = randGen.nextDouble() * 2 * Math.PI;
    			the = randGen.nextDouble() * Math.PI;
    			locs.add(sphloc(new Location[]{tempLoc}, phi, the, r)[0]);
    		}
    	}
    	return (Location[]) locs.toArray(new Location[locs.size()]);
    }
    
    public static Location[] getSphere(Location[] l, double r, double d){
    	List<Location> locs = new ArrayList<Location>();
    	double the = 0D;
    	double phi = 0D;
    	int n = (int) (Math.PI * r * d);
    	for(Location tempLoc : l){
    		the = 0;
    		for(int i = 0; i < n; i++){
    			phi = 0;
    			for(int j = 0; j < n * 2; j++){
    				locs.add(sphloc(new Location[]{tempLoc}, phi, the, r)[0]);
    				phi +=  Math.PI / n;
    			}
    			the += Math.PI / n;
    		}
    	}
    	return (Location[]) locs.toArray(new Location[locs.size()]);
    }
    
    
}