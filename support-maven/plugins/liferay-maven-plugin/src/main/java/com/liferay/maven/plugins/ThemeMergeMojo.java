/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.maven.plugins;

import java.io.File;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.codehaus.plexus.archiver.UnArchiver;
import org.codehaus.plexus.archiver.manager.ArchiverManager;
import org.codehaus.plexus.components.io.fileselectors.FileSelector;
import org.codehaus.plexus.components.io.fileselectors.IncludeExcludeFileSelector;

/**
 * <a href="ThemeMergeMojo.java.html"><b><i>View Source</i></b></a>
 *
 * @author Mika Koivisto
 * @goal   theme-merge
 * @phase  process-sources
 */
public class ThemeMergeMojo extends AbstractMojo {

	public void execute() throws MojoExecutionException {
		try {
			doExecute();
		}
		catch (Exception e) {
			throw new MojoExecutionException(e.getMessage(), e);
		}
	}

	protected void doExecute() throws Exception {
		if (!workDir.exists()) {
			workDir.mkdirs();
		}

		String parentThemeGroupId = "com.liferay.portal";
		String parentThemeArtifactId = "portal-web";
		String parentThemeVersion = liferayVersion;

		String[] excludes = {
			"html/themes/classic/_diffs/**",
			"html/themes/control_panel/_diffs/**"
		};

		String[] includes = {
			"html/themes/_unstyled/**", "html/themes/_styled/**",
			"html/themes/classic/**", "html/themes/control_panel/**"
		};

		if (!parentTheme.equals("_styled") &&
			!parentTheme.equals("_unstyled") &&
			!parentTheme.equals("classic") &&
			!parentTheme.equals("control_panel")) {

			String[] parentThemeArray = parentTheme.split(":");

			parentThemeGroupId = parentThemeArray[0];
			parentThemeArtifactId = parentThemeArray[1];
			parentThemeVersion = parentThemeArray[2];

			excludes = new String[] {"WEB-INF/**"};
		}

		Artifact artifact = artifactFactory.createArtifact(
			parentThemeGroupId, parentThemeArtifactId, parentThemeVersion, "",
			"war");

		artifactResolver.resolve(
			artifact, remoteArtifactRepositories, localArtifactRepository);

		UnArchiver unArchiver = archiverManager.getUnArchiver(
			artifact.getFile());

		unArchiver.setDestDirectory(workDir);
		unArchiver.setSourceFile(artifact.getFile());

		IncludeExcludeFileSelector includeExcludeFileSelector =
			new IncludeExcludeFileSelector();

		includeExcludeFileSelector.setExcludes(excludes);
		includeExcludeFileSelector.setIncludes(includes);

		unArchiver.setFileSelectors(
			new FileSelector[] {includeExcludeFileSelector});

		unArchiver.extract();

		webappDirectory.mkdirs();

		if (parentThemeArtifactId.equals("portal-web")) {
			FileUtils.copyDirectory(
				new File(workDir, "html/themes/_unstyled"), webappDirectory);

			getLog().info(
				"Copying html/themes/_unstyled to " + webappDirectory);

			if (!"_unstyled".equals(parentTheme)) {
				FileUtils.copyDirectory(
					new File(workDir, "html/themes/_styled"), webappDirectory);

				getLog().info(
					"Copying html/themes/_styled to " + webappDirectory);
			}

			if (!"_unstyled".equals(parentTheme) &&
				!"_styled".equals(parentTheme)) {

				FileUtils.copyDirectory(
					new File(workDir, "html/themes/" + parentTheme),
					webappDirectory);

				getLog().info(
					"Copying html/themes/" + parentTheme + " to " +
						webappDirectory);
			}
		}
		else {
			FileUtils.copyDirectory(workDir, webappDirectory);
		}

		File initFile = new File(
			webappDirectory, "templates/init." + themeType);

		FileUtils.deleteQuietly(initFile);

		File templatesDirectory = new File(webappDirectory, "templates/");

		String[] extensions = null;

		if (themeType.equals("ftl")) {
			extensions = new String[] {"vm"};
		}
		else {
			extensions = new String[] {"ftl"};
		}

		Iterator<File> itr = FileUtils.iterateFiles(
			templatesDirectory, extensions, false);

		while (itr.hasNext()) {
			File file = itr.next();

			FileUtils.deleteQuietly(file);
		}
	}

	/**
	 * @component
	 */
	private ArchiverManager archiverManager;

	/**
	 * @component
	 */
	private ArtifactFactory artifactFactory;

	/**
	 * @component
	 */
	private ArtifactResolver artifactResolver;

	/**
	 * @parameter
	 * @required
	 */
	private String liferayVersion;

	/**
	 * @parameter expression="${localRepository}"
	 * @readonly
	 * @required
	 */
	private ArtifactRepository localArtifactRepository;

	/**
	 * Parent theme. Can be _styled | _unstyled | classic | control_panel |
	 * artifactGroupId:artifactId:artifactVersion
	 *
	 * @parameter default-value="_styled"
	 */
	private String parentTheme;

	/**
	 * @parameter expression="${project.remoteArtifactRepositories}"
	 * @readonly
	 * @required
	 */
	private List remoteArtifactRepositories;

	/**
	 * @parameter default-value="vm"
	 * @required
	 */
	private String themeType;

	/**
	 * @parameter expression=
	 *			  "${project.build.directory}/${project.build.finalName}"
	 * @required
	 */
	private File webappDirectory;

	/**
	 * @parameter expression="${project.build.directory}/liferay-theme/work"
	 * @required
	 */
	private File workDir;

}