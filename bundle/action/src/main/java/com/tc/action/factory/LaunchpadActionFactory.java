/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action.factory;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;

import javax.servlet.jsp.PageContext;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.action.AdAction;
import com.tc.action.AdvertiseAction;
import com.tc.action.ArticleAction;
import com.tc.action.BreadCrumbAction;
import com.tc.action.CarouselAction;
import com.tc.action.CategoryAction;
import com.tc.action.ChartAction;
import com.tc.action.DynamicFlipbookAction;
import com.tc.action.EventAgendaAction;
import com.tc.action.EventInformationAction;
import com.tc.action.EventPreviewAction;
import com.tc.action.FacebookFeedAction;
import com.tc.action.FeaturedAction;
import com.tc.action.FlipbookAction;
import com.tc.action.FlipbooksListAction;
import com.tc.action.FreeHTMLAction;
import com.tc.action.FreeJavaScriptAction;
import com.tc.action.GalleryAction;
import com.tc.action.HelloWorldAction;
import com.tc.action.HeroBannerAction;
import com.tc.action.IframeAction;
import com.tc.action.ImageAction;
import com.tc.action.LatestArticlesAction;
import com.tc.action.LongArticlePropertiesAction;
import com.tc.action.MapAction;
import com.tc.action.MostCommentedArticleAction;
import com.tc.action.MostViewedAticleAction;
import com.tc.action.NewsAction;
import com.tc.action.SearchAction;
import com.tc.action.SectionNavigationAction;
import com.tc.action.ShortArticleAction;
import com.tc.action.SiteNavigationAction;
import com.tc.action.SocialFollowAction;
import com.tc.action.SocialLoginAction;
import com.tc.action.TableAction;
import com.tc.action.TopNavigationAction;
import com.tc.action.TwitterFeedAction;
import com.tc.action.VideoAction;
import com.tc.framework.api.ActionFactory;
import com.tc.framework.scripting.jsp.taglib.ActionTag;

@Component(enabled=true,immediate = true)
@Service(serviceFactory = false)
@Properties({
		@Property(name = Constants.SERVICE_VENDOR, value = "The Virtusa Corporation"),
		@Property(name = Constants.SERVICE_DESCRIPTION, value = "Launchpad ActionFactory"),
		@Property(name = "interface", value = "org.virtusa.framework.api.ActionFactory") })
public class LaunchpadActionFactory implements ActionFactory {

	private static final Logger log = LoggerFactory.getLogger(LaunchpadActionFactory.class);
	private ComponentContext context;

//	private static final Class<ActionTag> ACTION_TAG = ActionTag.class;

	@Property(name = "factoryname")
	public static final String FACTORY_NAME = LaunchpadActionFactory.class.getName();
	
	@Property(name = "actionclasses")
	public static final String[] ACTION_CLASSES = {HelloWorldAction.class.getName(),ShortArticleAction.class.getName(),
		EventAgendaAction.class.getName(),EventInformationAction.class.getName(),
		CarouselAction.class.getName(),ChartAction.class.getName(),NewsAction.class.getName(),TopNavigationAction.class.getName(),
		MapAction.class.getName(),SectionNavigationAction.class.getName(),HeroBannerAction.class.getName(),
		GalleryAction.class.getName(),SiteNavigationAction.class.getName(),SocialFollowAction.class.getName(),
		EventPreviewAction.class.getName(),BreadCrumbAction.class.getName(),SearchAction.class.getName(),CategoryAction.class.getName(),ArticleAction.class.getName(),SocialLoginAction.class.getName(),LongArticlePropertiesAction.class.getName(),TwitterFeedAction.class.getName(),MostViewedAticleAction.class.getName(),FacebookFeedAction.class.getName(),FeaturedAction.class.getName(),LatestArticlesAction.class.getName(),MostCommentedArticleAction.class.getName(),FreeHTMLAction.class.getName(),FreeJavaScriptAction.class.getName(),IframeAction.class.getName(),ImageAction.class.getName(),TableAction.class.getName(),VideoAction.class.getName(),FlipbookAction.class.getName(),DynamicFlipbookAction.class.getName(),FlipbooksListAction.class.getName(),AdvertiseAction.class.getName(),AdAction.class.getName()};



	private String bundleVersion;

	protected void activate(ComponentContext context) {
		this.context = context;
		this.bundleVersion = ((String) context.getBundleContext().getBundle()
				.getHeaders().get("Bundle-Version"));
	}
	
	/**
     * @param context Not used
     */
    protected void deactivate(ComponentContext context) {
        this.context = null;
    }

	@Override
	public Object invokeAction(Object sourceTag, String actionClassName,
			String actionName, PageContext pageContext) {
		log.info("Calling LaunchpadFacadeFactory:getAdapter"+ actionName);
		
		Object retobj = null;
		if ((sourceTag instanceof ActionTag)) {
			final ClassLoader tccl = this.getClass().getClassLoader();
			Class<?> cls;
			try {
				cls = Class.forName(actionClassName, true, tccl);
				Object obj;
				obj = cls.newInstance();
				final Class<?>[] params = new Class[1];
	            params[0] = PageContext.class;
	            final Object[] arglist = new Object[1];
	            arglist[0] = pageContext;
	            Method initMethod = cls.getMethod("init", params);
	            initMethod.invoke(obj, arglist);
				Method actionMethod  = cls.getMethod(actionName);
				retobj = actionMethod.invoke(obj);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				log.error(e.getMessage(), e);
			} catch (ClassNotFoundException e) {
				log.error(e.getMessage(), e);
			} catch (SecurityException e) {
				log.error(e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage(), e);
			} catch (InstantiationException e) {
				log.error(e.getMessage(), e);
			}
		}
		return retobj;
	}

}
