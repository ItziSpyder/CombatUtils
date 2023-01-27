package me.improper.combatutils.entity;

import org.bukkit.Location;

public class Hitbox {

    private double x, y, z, x1, y1, z1;
    private double hx, hy, hz, hx1, hy1, hz1;

    public Hitbox() {

    }

    public boolean inBounds(Location location) {
        return inBoundsBody(location) || inBoundsHead(location);
    }

    public boolean inBoundsBody(Location location) {
        double[] min = {Math.min(x,x1), Math.min(y,y1), Math.min(z,z1)};
        double[] max = {Math.max(x,x1), Math.max(y,y1), Math.max(z,z1)};
        return min[0] < max[0] && min[1] < max[1] && min[2] < max[2];
    }

    public boolean inBoundsHead(Location location) {
        double[] hMin = {Math.max(hx,hx1), Math.max(hy,hy1), Math.max(hz,hz1)};
        double[] hMax = {Math.max(hx,hx1), Math.max(hy,hy1), Math.max(hz,hz1)};
        return hMin[0] < hMax[0] && hMin[1] < hMax[1] && hMin[2] < hMax[2];
    }

    public double getZ() {
        return z;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getZ1() {
        return z1;
    }

    public double getHx() {
        return hx;
    }

    public double getHy() {
        return hy;
    }

    public double getHz() {
        return hz;
    }

    public double getHx1() {
        return hx1;
    }

    public double getHy1() {
        return hy1;
    }

    public double getHz1() {
        return hz1;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setZ1(double z1) {
        this.z1 = z1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setHx(double hx) {
        this.hx = hx;
    }

    public void setHy(double hy) {
        this.hy = hy;
    }

    public void setHx1(double hx1) {
        this.hx1 = hx1;
    }

    public void setHy1(double hy1) {
        this.hy1 = hy1;
    }

    public void setHz1(double hz1) {
        this.hz1 = hz1;
    }

    public void setHz(double hz) {
        this.hz = hz;
    }

}
