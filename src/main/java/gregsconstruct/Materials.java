package gregsconstruct;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.shared.TinkerCommons;

import java.util.ArrayList;

public class Materials {
    public static ArrayList<slimeknights.tconstruct.library.materials.Material> ingotMaterials = new ArrayList<>();
    public static ArrayList<IngotMaterial> GtIngotmaterials = new ArrayList<>();

    public static ArrayList<slimeknights.tconstruct.library.materials.Material> gemMaterials = new ArrayList<>();
    public static ArrayList<GemMaterial> GtGemmaterials = new ArrayList<>();

    public static void preInit() {
        for (Material mat : Material.MATERIAL_REGISTRY) {
            if (mat instanceof IngotMaterial && mat != gregtech.api.unification.material.Materials.Iron && mat != gregtech.api.unification.material.Materials.Cobalt && mat != gregtech.api.unification.material.Materials.Copper && mat != gregtech.api.unification.material.Materials.Bronze && mat != gregtech.api.unification.material.Materials.Lead && mat != gregtech.api.unification.material.Materials.Electrum && mat != gregtech.api.unification.material.Materials.Silver && mat != gregtech.api.unification.material.Materials.Steel && mat != gregtech.api.unification.material.Materials.PigIron) {
                if (((SolidMaterial) mat).toolDurability > 0) {
                    ingotMaterials.add(new slimeknights.tconstruct.library.materials.Material(mat.toString(), mat.materialRGB).setCastable(true).setCraftable(false));
                    GtIngotmaterials.add((IngotMaterial) mat);
                } else
                    TinkerRegistry.integrate(((IngotMaterial) mat).getMaterialFluid(), upperCase(mat));
                if (((IngotMaterial) mat).blastFurnaceTemperature <= 0) {
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.ore, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).smeltingMultiplier * Config.oreToIngotRatio));
                }
            }
            if (mat instanceof GemMaterial && ((GemMaterial) mat).toolDurability > 0) {
                gemMaterials.add(new slimeknights.tconstruct.library.materials.Material(mat.toString(), mat.materialRGB).setCastable(false).setCraftable(true));
                GtGemmaterials.add((GemMaterial) mat);
            }
            if (mat instanceof DustMaterial && !(mat instanceof IngotMaterial)) {
                DustMaterial dust = (DustMaterial) mat;
                if (dust.directSmelting != null) {
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.ore, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                } else if (dust.hasFlag(DustMaterial.MatFlags.SMELT_INTO_FLUID)) {
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.ore, mat).toString(), dust.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), dust.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), dust.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), dust.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), dust.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), dust.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), dust.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), dust.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), dust.getMaterialFluid(), (int) (144 * dust.smeltingMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dust, mat).toString(), dust.getMaterialFluid(), 144);
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dustSmall, mat).toString(), dust.getMaterialFluid(), 36);
                }
            }
        }

        for (int i = 0; i < ingotMaterials.size(); i++) {
            slimeknights.tconstruct.library.materials.Material mat = ingotMaterials.get(i);
            IngotMaterial GtMat = GtIngotmaterials.get(i);
            mat.addCommonItems(upperCase(GtMat));
            mat.setFluid(GtMat.getMaterialFluid());
            mat.addItemIngot(new UnificationEntry(OrePrefix.ingot, GtMat).toString());
            mat.setRepresentativeItem(OreDictUnifier.get(OrePrefix.ingot, GtMat));
            TinkerRegistry.addMaterial(mat);
            TinkerRegistry.addMaterialStats(mat,
                    new HeadMaterialStats(GtMat.toolDurability, GtMat.toolSpeed, GtMat.toolAttackDamage, GtMat.harvestLevel),
                    new HandleMaterialStats((float) (((double) GtMat.toolSpeed / GtMat.toolDurability) * 40), GtMat.toolDurability / 4),
                    new ExtraMaterialStats(GtMat.toolDurability / 100));
            mat.setRenderInfo(GtMat.materialRGB);
            TinkerRegistry.integrate(mat, mat.getFluid(), upperCase(GtMat));
        }


        for (int i = 0; i < gemMaterials.size(); i++) {
            slimeknights.tconstruct.library.materials.Material mat = gemMaterials.get(i);
            GemMaterial GtMat = GtGemmaterials.get(i);
            mat.addCommonItems(upperCase(GtMat));
            mat.addItemIngot(new UnificationEntry(OrePrefix.gem, GtMat).toString());
            mat.setRepresentativeItem(OreDictUnifier.get(OrePrefix.gem, GtMat));
            TinkerRegistry.addMaterial(mat);
            TinkerRegistry.addMaterialStats(mat,
                    new HeadMaterialStats(GtMat.toolDurability, GtMat.toolSpeed, GtMat.toolAttackDamage, GtMat.harvestLevel),
                    new HandleMaterialStats((float) (((double) GtMat.toolSpeed / GtMat.toolDurability) * 40), GtMat.toolDurability / 4),
                    new ExtraMaterialStats(GtMat.toolDurability / 100));
            mat.setRenderInfo(GtMat.materialRGB);
            TinkerRegistry.integrate(mat, upperCase(GtMat));
        }

        TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.Brass.getFluid(4),
                gregtech.api.unification.material.Materials.Copper.getFluid(3),
                gregtech.api.unification.material.Materials.Zinc.getFluid(1));

        TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.Cupronickel.getFluid(2),
                gregtech.api.unification.material.Materials.Copper.getFluid(1),
                gregtech.api.unification.material.Materials.Nickel.getFluid(1));

        TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.RedAlloy.getFluid(1),
                gregtech.api.unification.material.Materials.Copper.getFluid(1),
                gregtech.api.unification.material.Materials.Redstone.getFluid(4));

        TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.TinAlloy.getFluid(2),
                gregtech.api.unification.material.Materials.Iron.getFluid(1),
                gregtech.api.unification.material.Materials.Tin.getFluid(1));

        TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.BatteryAlloy.getFluid(5),
                gregtech.api.unification.material.Materials.Lead.getFluid(4),
                gregtech.api.unification.material.Materials.Antimony.getFluid(1));

        TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.SolderingAlloy.getFluid(10),
                gregtech.api.unification.material.Materials.Tin.getFluid(9),
                gregtech.api.unification.material.Materials.Antimony.getFluid(1));

        TinkerRegistry.registerAlloy(gregtech.api.unification.material.Materials.Magnalium.getFluid(3),
                gregtech.api.unification.material.Materials.Aluminium.getFluid(2),
                gregtech.api.unification.material.Materials.Magnesium.getFluid(1));

        TinkerRegistry.registerMelting("sand", gregtech.api.unification.material.Materials.Glass.getMaterialFluid(), 144);
        TinkerRegistry.registerMelting("blockGlass", gregtech.api.unification.material.Materials.Glass.getMaterialFluid(), 144);
        TinkerRegistry.registerMelting("paneGlass", gregtech.api.unification.material.Materials.Glass.getMaterialFluid(), 54);
        TinkerRegistry.registerTableCasting(new CastingRecipe(new ItemStack(Blocks.GLASS_PANE), null, gregtech.api.unification.material.Materials.Glass.getMaterialFluid(), 54));
    }

    public static void postInit() {
        TinkerRegistry.registerBasinCasting(new CastingRecipe(new ItemStack(TinkerCommons.blockClearGlass), gregtech.api.unification.material.Materials.Glass.getMaterialFluid(), 144, 120));
    }

    private static String upperCase(Material mat) {
        return mat.toCamelCaseString().substring(0, 1).toUpperCase() + mat.toCamelCaseString().substring(1);
    }
}
