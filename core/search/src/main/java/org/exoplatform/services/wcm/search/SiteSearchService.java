/*
 * Copyright (C) 2003-2008 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.services.wcm.search;

import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.wcm.search.base.AbstractPageList;

/**
 * SiteSearchService is used in the Search portlet that allows users to find all information matching with your given keyword.
 * 
 * Created by The eXo Platform SAS
 * Author : Hoa Pham
 * hoa.pham@exoplatform.com
 * Oct 7, 2008
 */
public interface SiteSearchService {

  public final static String PAGE_MODE_NONE = "none";
  public final static String PAGE_MODE_MORE = "more";
  public final static String PAGE_MODE_PAGINATION = "pagination";  
  
  /**
   * Adds the exclude, include data type plugin.
   *
   * @param plugin The plugin
   */
  public void addExcludeIncludeDataTypePlugin(ExcludeIncludeDataTypePlugin plugin);

  /**
   * Find all child nodes whose contents match with the given keyword. These nodes will be put in the list of search results.
   *
   * @param queryCriteria The query criteria for SiteSearchService. Basing on search criteria, SiteSearchService can easily create the query statement to search.
   * @param sessionProvider The session provider. 
   * @param pageSize The number of search results on a page.
   *
   * @return the wCM paginated query result
   *
   * @throws Exception the exception
   */
  public AbstractPageList<ResultNode> searchSiteContents(SessionProvider sessionProvider,
                                                    QueryCriteria queryCriteria,
                                                    int pageSize,
                                                    boolean isSearchContent) throws Exception;
  
  /**
   * 
   * 
   * @param sessionProvider
   * @param queryCriteria
   * @param pageSize
   * @param isSearchContent
   * @return
   * @throws Exception
   */
  public AbstractPageList<ResultNode> searchPageContents(SessionProvider sessionProvider,
                                                      QueryCriteria queryCriteria,
                                                      int pageSize,
                                                      boolean isSearchContent) throws Exception;
}
