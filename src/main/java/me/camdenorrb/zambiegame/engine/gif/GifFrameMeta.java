package me.camdenorrb.zambiegame.engine.gif;

import me.camdenorrb.zambiegame.struct.LazyStruct;
import me.camdenorrb.zambiegame.utils.LazyUtils;
import me.camdenorrb.zambiegame.utils.TryUtils;
import org.w3c.dom.Node;

import javax.imageio.metadata.*;


public class GifFrameMeta {

	private IIOMetadata metadata;


	private final LazyStruct<IIOMetadataNode> metadataTree = LazyUtils.lazy(() ->
		(IIOMetadataNode) metadata.getAsTree(getNativeMetadataFormatName())
	);

	private final LazyStruct<IIOMetadataNode> firstNode = LazyUtils.lazy(() ->
		(IIOMetadataNode) metadataTree.get().item(1)
	);


	public GifFrameMeta(IIOMetadata metadata) {
		this.metadata = metadata;
	}


	public boolean isStandardMetadataFormatSupported() {
		return metadata.isStandardMetadataFormatSupported();
	}

	public boolean isReadOnly() {
		return metadata.isReadOnly();
	}

	public String getNativeMetadataFormatName() {
		return metadata.getNativeMetadataFormatName();
	}

	public String[] getExtraMetadataFormatNames() {
		return metadata.getExtraMetadataFormatNames();
	}

	public String[] getMetadataFormatNames() {
		return metadata.getMetadataFormatNames();
	}

	public IIOMetadataFormat getMetadataFormat(String formatName) {
		return metadata.getMetadataFormat(formatName);
	}

	public IIOMetadataNode getAsTree() {
		return metadataTree.get();
	}

	public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
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
	}


	public IIOMetadata getImageMeta() {
		return metadata;
	}

	public void setDelay(int value) {
		firstNode.get().setAttribute("delayTime", String.valueOf(value));
	}

	public int getDelay() {
		final IIOMetadataNode node = getNode("GraphicControlExtension");
		final String delay = node.getAttribute("delayTime");
		return TryUtils.attemptOrDefault(0, () -> Integer.valueOf(delay));
	}

	public IIOMetadataNode getNode(String nodeName) {

		final int nodeLength = metadataTree.get().getLength();

		for (int i = 0; i < nodeLength; i++) {
			final IIOMetadataNode node = (IIOMetadataNode) metadataTree.get().item(i);
			if (node.getNodeName().equalsIgnoreCase(nodeName)) return node;
		}

		return null;
	}

}
