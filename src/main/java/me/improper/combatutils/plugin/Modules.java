package me.improper.combatutils.plugin;

import me.improper.combatutils.plugin.modules.*;

import java.util.ArrayList;
import java.util.List;

public enum Modules implements Cloneable {

    ANCHORAURA(AnchorAura.class, new AnchorAura()),
    AUTOTOTEM(AutoTotem.class, new AutoTotem()),
    CRYSTALAURA(CrystalAura.class, new CrystalAura()),
    DODGE(Dodge.class, new Dodge()),
    FLIGHT(Flight.class, new Flight()),
    KILLAURA(KillAura.class, new KillAura()),
    REACH(Reach.class, new Reach()),
    SPEED(Speed.class, new Speed()),
    CRITICALS(Criticals.class, new Criticals()),
    NOFALL(NoFall.class, new NoFall()),
    AIMBOT(AimBot.class, new AimBot()),
    FASTCRYSTAL(FastCrystal.class, new FastCrystal()),
    ANTIKB(AntiKb.class, new AntiKb());

    private Class<? extends Module> moduleClass;
    private Module module;

    Modules(Class<? extends Module> clazz, Module module) {
        this.moduleClass = clazz;
        this.module = module;
    }

    public Class<? extends Module> getModuleClass() {
        return moduleClass;
    }

    public Module getModule() {
        return module;
    }

    public static List<String> stringValues() {
        List<String> list = new ArrayList<>();
        for (Modules mod : Modules.values()) list.add(mod.name().toLowerCase());
        return list;
    }
}
