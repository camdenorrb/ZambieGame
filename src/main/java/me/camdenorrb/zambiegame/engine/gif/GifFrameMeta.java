package me.camdenorrb.zambiegame.engine.gif;

import me.camdenorrb.zambiegame.struct.lazy.LazyStruct;
import me.camdenorrb.zambiegame.utils.TryUtils;

import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataFormat;
import javax.imageio.metadata.IIOMetadataNode;

import static me.camdenorrb.zambiegame.utils.LazyUtils.lazy;


/**
 * Represents the meta for a frame
 */
public class GifFrameMeta {

	private IIOMetadata metadata;


	private final LazyStruct<IIOMetadataNode> metadataTree = lazy(() ->
		(IIOMetadataNode) metadata.getAsTree(getNativeMetadataFormatName())
	);

	private final LazyStruct<IIOMetadataNode> firstNode = lazy(() ->
		(IIOMetadataNode) metadataTree.get().item(1)
	);

	private final LazyStruct<IIOMetadataNode> graphicControlExt = lazy(() ->
			findNode("GraphicControlExtension")
	);


	/**
	 * Constructs frame metadata
	 *
	 * @param metadata The image metadata to refer off of
	 */
	public GifFrameMeta(IIOMetadata metadata) {
		this.metadata = metadata;
	}


	/**
	 * Checks if the standard metadata format is supported
	 *
	 * @return If the standard metadata format is supported
	 */
	public boolean isStandardMetadataFormatSupported() {
		return metadata.isStandardMetadataFormatSupported();
	}

	/**
	 * Checks if the metadata is read only
	 *
	 * @return If the metadata is read only
	 */
	public boolean isReadOnly() {
		return metadata.isReadOnly();
	}

	/**
	 * Gets the native metadata format name
	 *
	 * @return The native metadata format name
	 */
	public String getNativeMetadataFormatName() {
		return metadata.getNativeMetadataFormatName();
	}

	/**
	 * Gets the extra metadata format names
	 *
	 * @return The extra metadata format names
	 */
	public String[] getExtraMetadataFormatNames() {
		return metadata.getExtraMetadataFormatNames();
	}

	/**
	 * Gets the metadata format names
	 *
	 * @return The metadata format names
	 */
	public String[] getMetadataFormatNames() {
		return metadata.getMetadataFormatNames();
	}

	/**
	 * The metadata for a given format name
	 *
	 * @param formatName The format name to get meta from
	 *
	 * @return The metadata for the format name
	 */
	public IIOMetadataFormat getMetadataFormat(String formatName) {
		return metadata.getMetadataFormat(formatName);
	}

	/**
	 * Returns a tree of the metadata
	 *
	 * @return A tree of the metadata
	 */
	public IIOMetadataNode getAsTree() {
		return metadataTree.get();
	}


/*	public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
		metadata.mergeTree(formatName, root);
	}

	public void setFromTree(String formatName, Node root) throws IIOInvalidTreeException {
		metadata.setFromTree(formatName, root);
	}

	public void reset() {
		metadata.reset();
	}

	public void setController(IIOMetadataController controller) {
		metadata.setController(controller);
	}

	public IIOMetadataController getController() {
		return metadata.getController();
	}

	public IIOMetadataController getDefaultController() {
		return metadata.getDefaultController();
	}

	public boolean hasController() {
		return metadata.hasController();
	}

	public boolean activateController() {
		return metadata.activateController();
	}*/

	/**
	 * Gets the image metadata
	 *
	 * @return The image metadata
	 */
	public IIOMetadata getImageMeta() {
		return metadata;
	}

	/**
	 * Sets the delay for the frame
	 *
	 * @param value The new delay for the frame
	 */
	public void setDelay(int value) {
		graphicControlExt.get().setAttribute("delayTime", String.valueOf(value));
	}

	/**
	 * Gets the delay for the frame
	 *
	 * @return The delay for the frame
	 */
	public int getDelay() {
		final IIOMetadataNode node = graphicControlExt.get();
		final String delay = node.getAttribute("delayTime");
		return TryUtils.attemptOrDefault(0, () -> Integer.valueOf(delay));
	}

	/**
	 * Finds a node with a given name
	 *
	 * @param nodeName The node name to look for
	 *
	 * @return A node with that name or null
	 */
	public IIOMetadataNode findNode(String nodeName) {

		final int nodeLength = metadataTree.get().getLength();

		for (int i = 0; i < nodeLength; i++) {
			final IIOMetadataNode node = (IIOMetadataNode) metadataTree.get().item(i);
			if (node.getNodeName().equalsIgnoreCase(nodeName)) return node;
		}

		return null;
	}

}
