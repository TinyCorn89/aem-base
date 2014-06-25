package com.domain.cq.company.common.tags;

import com.day.cq.wcm.foundation.Image;
import javax.servlet.jsp.JspException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetImageTag extends CQBaseTag {

	protected static Logger log = LoggerFactory.getLogger(GetImageTag.class);

	@Override
	public int doStartTag() throws JspException {

		Image image = new Image(resource, "image");
		String imageSrc = null;

		if (image.hasContent()) {
			log.info("Image Exists");
			image.loadStyleData(currentStyle);

			if (!currentDesign.equals(resourceDesign)) {
				image.setSuffix(currentDesign.getId());
			}

			imageSrc = image.getSrc();
		}

		if (imageSrc != null) {

			if(imageSrc.indexOf("image/file.png")!= -1){
				imageSrc = imageSrc.replace("image/file.png", "image.img.png");
			}
			if(imageSrc.indexOf("image/file.gif")!= -1){
				imageSrc = imageSrc.replace("image/file.gif", "image.img.png");
			}

			pageContext.setAttribute("imageSrc", imageSrc);

		}
		return super.doStartTag();
	}
}
