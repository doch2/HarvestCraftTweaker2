package tfar.harvestcrafttweaker.asm;


import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class Transformer implements IFMLLoadingPlugin {
	private File[] modFileList = new File[500];
	private Integer modFileListNum = 0;

	public Transformer() {
		// TODO Hack to bring up Pam's Harvestcraft for some finger-licking-good mixins
		// Thanks to ClientHax
		try {
			File defaultModsFolder = new File("./mods");
			for (File file : defaultModsFolder.listFiles()) {
				modFileList[modFileListNum] = file;
				modFileListNum += 1;
			}

			String heliosLauncherModsFolderPath = System.getenv("APPDATA") + "\\.eternalauncher\\common\\modstore";
			File heliosLauncherModsFolder = new File(heliosLauncherModsFolderPath);
			if (heliosLauncherModsFolder.exists()) {
				getAllFilesInDIrWithFilter(heliosLauncherModsFolderPath, ".jar");
			}

			for (File file : modFileList) {
				if (file != null) {
					if (file.getName().startsWith("Pam's HarvestCraft 1.12.2") || file.getName().startsWith("Pam's+HarvestCraft+1.12.2") || file.getName().startsWith("Pam's+HarvestCraft-1.12.2")) {
						loadModJar(file);
						System.out.println("Found Harvestcraft Mod File!");
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		MixinBootstrap.init();
		Mixins.addConfiguration("mixins.harvestcrafttweaker.json");
	}

	public void getAllFilesInDIrWithFilter(String dirPath, String fileExtension) {
		File dir = new File(dirPath);
		File files[] = dir.listFiles();

		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				getAllFilesInDIrWithFilter(file.getPath(), fileExtension);
			} else if (file.getName().endsWith(fileExtension)) {
				modFileList[modFileListNum] = file;
				modFileListNum += 1;
			}
		}
	}

	/**
	 * Return a list of classes that implements the IClassTransformer interface
	 *
	 * @return a list of classes that implements the IClassTransformer interface
	 */
	@Override
	public String[] getASMTransformerClass()
	{
		return new String[0];
	}

	/**
	 * Return a class name that implements "ModContainer" for injection into the mod list
	 * The "getName" function should return a name that other mods can, if need be,
	 * depend on.
	 * Trivially, this modcontainer will be loaded before all regular mod containers,
	 * which means it will be forced to be "immutable" - not susceptible to normal
	 * sorting behaviour.
	 * All other mod behaviours are available however- this container can receive and handle
	 * normal loading events
	 */
	@Override
	public String getModContainerClass()
	{
		return null;
	}

	/**
	 * Return the class name of an implementor of "IFMLCallHook", that will be run, in the
	 * main thread, to perform any additional setup this coremod may require. It will be
	 * run <strong>prior</strong> to Minecraft starting, so it CANNOT operate on minecraft
	 * itself. The game will deliberately crash if this code is detected to trigger a
	 * minecraft class loading
	 * TODO: implement crash ;)
	 */
	@Nullable
	@Override
	public String getSetupClass()
	{
		return null;
	}

	/**
	 * Inject coremod data into this coremod
	 * This data includes:
	 * "mcLocation" : the location of the minecraft directory,
	 * "coremodList" : the list of coremods
	 * "coremodLocation" : the file this coremod loaded from,
	 *
	 * @param data
	 */
	@Override
	public void injectData(Map<String, Object> data)
	{

	}

	private void loadModJar(File jar) throws Exception {
		((LaunchClassLoader) this.getClass().getClassLoader()).addURL(jar.toURI().toURL());
		CoreModManager.getReparseableCoremods().add(jar.getName());
	}

	/**
	 * Return an optional access transformer class for this coremod. It will be injected post-deobf
	 * so ensure your ATs conform to the new srgnames scheme.
	 *
	 * @return the name of an access transformer class or null if none is provided
	 */
	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}

}