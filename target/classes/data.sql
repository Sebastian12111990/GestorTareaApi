

INSERT INTO public.estado_tarea (estado)
SELECT 'Pendiente'
WHERE NOT EXISTS (
    SELECT 1 FROM public.estado_tarea WHERE estado = 'Pendiente'
);

INSERT INTO public.estado_tarea (estado)
SELECT 'En Progreso'
WHERE NOT EXISTS (
    SELECT 1 FROM public.estado_tarea WHERE estado = 'En Progreso'
);

INSERT INTO public.estado_tarea (estado)
SELECT 'Completada'
WHERE NOT EXISTS (
    SELECT 1 FROM public.estado_tarea WHERE estado = 'Completada'
);

