package edu.cibrertec.sistemaVentas.service;

import edu.cibrertec.sistemaVentas.model.Empleado;
import edu.cibrertec.sistemaVentas.repository.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private IEmpleadoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = repository.findByUsername(username);
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        //String pass = encode.encode("master"); -> $2a$10$iO5W.dxsBDbmpGSfnQh9FeL4P7U.OULWpOKmp5HD0SmcR0x2e3ZWG
        User.UserBuilder userBuilder = null;

        if (empleado != null) {
            userBuilder = User.withUsername(username);
            userBuilder.disabled(false);
            userBuilder.password(empleado.getPassword());
            userBuilder.authorities(empleado.getTipo().equals("1") ? new SimpleGrantedAuthority("ADMIN") : new SimpleGrantedAuthority("USER") );

            ServletRequestAttributes att = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession sessionUser = att.getRequest().getSession(true);
            sessionUser.setAttribute("usuarioSesion", empleado);
        } else {
            throw new UsernameNotFoundException("USUARIO NO ENCONTRADO");
        }

        return userBuilder.build();
    }
}
