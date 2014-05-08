package servlety.filtery;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import dao.beany.Akce;
import dao.beany.Chyby;
import dao.beany.Oznameni;

public class EncodingFilter implements Filter {
	private String encoding;
	private String baseUrl;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.encoding = fConfig.getInitParameter("encoding");
		this.baseUrl = fConfig.getServletContext().getContextPath() + "/";
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(this.encoding);
		response.setCharacterEncoding(this.encoding);

		request.setAttribute("akce", new Akce());
		request.setAttribute("chyby", new Chyby());
		request.setAttribute("oznameni", new Oznameni());
		request.setAttribute("baseUrl", baseUrl);

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// Nepouzito
	}
}
