INSERT INTO public.estado_tarea (estado, created_at, updated_at)
SELECT 'Pendiente', now(), now()
WHERE NOT EXISTS (
    SELECT 1 FROM public.estado_tarea WHERE estado = 'Pendiente'
);

INSERT INTO public.estado_tarea (estado, created_at, updated_at)
SELECT 'En Progreso', now(), now()
WHERE NOT EXISTS (
    SELECT 1 FROM public.estado_tarea WHERE estado = 'En Progreso'
);

INSERT INTO public.estado_tarea (estado, created_at, updated_at)
SELECT 'Completada', now(), now()
WHERE NOT EXISTS (
    SELECT 1 FROM public.estado_tarea WHERE estado = 'Completada'
);

INSERT INTO public.usuario (id, nombre, apellido, password, email, created_at, updated_at)
SELECT 1, 'Sebastian', 'Perez', 'encoded_12345', 'seba@gmail.com', now(), now()
WHERE NOT EXISTS (
    SELECT 1 FROM public.usuario WHERE email = 'seba@gmail.com'
);

INSERT INTO public.usuario (id, nombre, apellido, password, email, created_at, updated_at)
SELECT 2, 'Luis', 'Pruebas', 'encoded_12345', 'pruebas@gmail.com', now(), now()
WHERE NOT EXISTS (
    SELECT 1 FROM public.usuario WHERE email = 'pruebas@gmail.com'
);

INSERT INTO public.perfil(id, nombre_perfil, description, created_at, updated_at)
SELECT 1, 'ROLE_ADMIN', 'ROLE_ADMIN', now(), now()
WHERE NOT EXISTS (
    SELECT 1 FROM public.perfil WHERE nombre_perfil = 'ROLE_ADMIN'
);

INSERT INTO public.perfil (id, nombre_perfil, description, created_at, updated_at)
SELECT 2, 'ROLE_USER', 'ROLE_USER', now(), now()
WHERE NOT EXISTS (
    SELECT 1 FROM public.perfil WHERE nombre_perfil = 'ROLE_USER'
);

INSERT INTO usuarios_perfiles (usuario_id, perfil_id, created_at, updated_at)
SELECT 1, 1, now(), now()
WHERE NOT EXISTS (
    SELECT 1 FROM usuarios_perfiles WHERE usuario_id = 1 AND perfil_id = 1
);

INSERT INTO usuarios_perfiles (usuario_id, perfil_id, created_at, updated_at)
SELECT 2, 2, now(), now()
WHERE NOT EXISTS (
    SELECT 1 FROM usuarios_perfiles WHERE usuario_id = 2 AND perfil_id = 2
);
