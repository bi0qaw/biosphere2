package io.github.bi0qaw.biosphere.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;

public class LocationLib {

	public static Location[] getCircle(Location[] locations, double radius, double density) {
		Vector[] circle = VectorLib.getCircle(radius, density);
		return offset(locations, circle);
	}

	public static Location[] getBox(Location location1, Location location2) {
		Vector[] box = VectorLib.getBox(location1.toVector(), location2.toVector());
		return toLocation(box, location1.getWorld());
	}

	public static Location[] getBoxOutline(Location location1, Location location2, double density) {
		Vector[] boxoutline = VectorLib.getBoxOutline(location1.toVector(), location2.toVector(), density);
		return toLocation(boxoutline, location1.getWorld());
	}

	public static Location[] getCube(Location[] locations, double radius) {
		Vector[] cube = VectorLib.getCube(radius);
		return offset(locations, cube);
	}

	public static Location[] getCubeOutline(Location[] locations, double radius, double density) {
		Vector[] cubeoutline = VectorLib.getCubeOutline(radius, density);
		return offset(locations, cubeoutline);
	}

	public static Location[] getCylindricalCoordinate(Location[] locations, double radius, double yaw, double height) {
		return offset(locations, VectorMath.fromCylindricalCoordinates(radius, yaw, height));
	}

	public static Location[] getHelix(Location[] locations, double radius, double height, double step, double density) {
		Vector[] helix = VectorLib.getHelix(radius, height, step, density);
		return offset(locations, helix);
	}

	public static Location[] getLine(Location[] locations, Location end, double density) {
		Vector origin = new Vector(0, 0, 0);
		Vector vector;
		ArrayList<Location> line = new ArrayList<Location>();
		for (Location l: locations) {
			vector = end.toVector().subtract(l.toVector());
			line.addAll(Arrays.asList(LocationLib.offset(new Location[] {l}, VectorLib.getLine(origin, vector, density))));
		}
		return line.toArray(new Location[line.size()]);
	}

	public static Location[] getLineCoordinate(Location[] locations, Location end, double position) {
		Location[] coordinates = new Location[locations.length];
		Vector delta;
		int i = 0;
		for (Location l: locations) {
			delta = end.toVector().subtract(l.toVector()).multiply(position);
			locations[i] = l.clone().add(delta);
			i++;
		}
		return locations;
	}

	public static Location[] getPath(Location[] locations, double density) {
		if (locations.length > 0) {
			Vector[] path = VectorLib.getPath(VectorLib.toVector(locations), density);
			World world = locations[0].getWorld();
			return toLocation(path, world);
		}
		else {
			return new Location[]{};
		}
	}

	public static Location[] getPolygon(Location[] locations, int points, double radius) {
		Vector[] polygon = VectorLib.getPolygon(points, radius);
		return offset(locations, polygon);
	}

	public static Location[] getPolygonOutline(Location[] locations, int points, double radius, double density) {
		Vector[] polygonoutline = VectorLib.getPolygonOutline(points, radius, density);
		return offset(locations, polygonoutline);
	}

	public static Location[] getRandomSphere(Location[] locations, double radius, double density) {
		Vector[] sphere = VectorLib.getRandomSphere(radius, density);
		return offset(locations, sphere);
	}

	public static Location[] getSphere(Location[] locations, double radius, double density) {
		Vector[] sphere = VectorLib.getSphere(radius, density);
		return offset(locations, sphere);
	}

	public static Location[] getSphericalCoordinates(Location[] locations, double radius, double yaw, double pitch) {
		Vector spherical = VectorMath.fromSphericalCoordinates(radius, yaw, pitch);
		return offset(locations, spherical);
	}

	public static Location[] linkAll(Location[] locations, double density) {
		if (locations.length > 0) {
			Vector[] vectors = VectorLib.toVector(locations);
			Vector[] linked = VectorLib.linkAll(vectors, density);
			World world = locations[0].getWorld();
			return toLocation(linked, world);
		}
		else {
			return new Location[] {};
		}
	}

	public static Location midpoint(Location[] locations) {
		if (locations.length > 0) {
			Vector[] vectors = VectorLib.toVector(locations);
			Vector midpoint = VectorLib.midpoint(vectors);
			return midpoint.toLocation(locations[0].getWorld());
		}
		else {
			return null;
		}
	}

	public static Location[] offset(Location[] locations, Vector vector) {
		return offset(locations, new Vector[]{vector});
	}

	public static Location[] offset(Location[] locations, Vector[] vectors) {
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

	public static Location[] pointReflection(Location[] locations, Location point) {
		if (locations.length > 0) {
			Vector[] vectors = VectorLib.toVector(locations);
			Vector[] reflection = VectorLib.pointReflection(vectors, point.toVector());
			return toLocation(reflection, locations[0].getWorld());
		}
		else {
			return new Location[]{};
		}
	}

	public static Location[] reflection(Location[] locations, Location center, Vector direction) {
		if (locations.length > 0) {
			Vector[] vectors = VectorLib.toVector(locations);
			Vector[] reflection = VectorLib.reflection(vectors, center.toVector(), direction);
			return toLocation(reflection, locations[0].getWorld());
		}
		else {
			return new Location[]{};
		}
	}

	public static Location[] rotate(Location[] locations, Location center, Vector axis, double angle) {
		if (locations.length > 0) {
			Vector[] vectors = VectorLib.toVector(offset(locations, center.toVector().multiply(-1))); //move locations to origin & convert to vectors
			vectors = VectorLib.rotate(vectors, axis, angle);
			return offset(toLocation(vectors, locations[0].getWorld()), center.toVector());
		}
		else {
			return new Location[] {};
		}
	}

	public static Location[] rotateX(Location[] locations, Location center, double angle) {
		if (locations.length > 0) {
			Vector[] vectors = VectorLib.toVector(offset(locations, center.toVector().multiply(-1)));
			VectorLib.rotateX(vectors, angle);
			return offset(toLocation(vectors, locations[0].getWorld()), center.toVector());
		}
		else {
			return new Location[]{};
		}
	}

	public static Location[] rotateY(Location[] locations, Location center, double angle) {
		if (locations.length > 0) {
			Vector[] vectors = VectorLib.toVector(offset(locations, center.toVector().multiply(-1)));
			VectorLib.rotateY(vectors, angle);
			return offset(toLocation(vectors, locations[0].getWorld()), center.toVector());
		}
		else {
			return new Location[]{};
		}
	}

	public static Location[] rotateZ(Location[] locations, Location center, double angle) {
		if (locations.length > 0) {
			Vector[] vectors = VectorLib.toVector(offset(locations, center.toVector().multiply(-1)));
			VectorLib.rotateZ(vectors, angle);
			return offset(toLocation(vectors, locations[0].getWorld()), center.toVector());
		}
		else {
			return new Location[]{};
		}
	}

	public static Location[] scale(Location[] locations, Location center, double factor) {
		if (locations.length > 0) {
			Vector[] vectors = VectorLib.toVector(offset(locations, center.toVector().multiply(-1)));
			VectorLib.scale(vectors, factor);
			return offset(toLocation(vectors, locations[0].getWorld()), center.toVector());
		}
		else {
			return new Location[]{};
		}
	}

	public static Location[] scaleDirectional(Location[] locations, Location center, Vector direction, double factor) {
		if (locations.length > 0) {
			Vector[] vectors = VectorLib.toVector(offset(locations, center.toVector().multiply(-1)));
			VectorLib.scaleDirectional(vectors, direction, factor);
			return offset(toLocation(vectors, locations[0].getWorld()), center.toVector());
		}
		else {
			return new Location[]{};
		}
	}

	public static Location[] toLocation(Vector[] vectors, World world) {
		Location[] locations = new Location[vectors.length];
		int i = 0;
		for (Vector v: vectors) {
			locations[i] = v.toLocation(world);
			i++;
		}
		return locations;
	}

}
