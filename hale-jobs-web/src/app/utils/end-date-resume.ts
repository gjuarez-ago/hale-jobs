export function getEndDate(fechaMilisegundos: any): string {
  const todayMil: number = new Date().getTime();

  let diferecia: number = fechaMilisegundos - todayMil;
  let diasRestantes: number = Math.round(diferecia / (1000 * 3600 * 24));
  console.log({
    recibio: new Date(fechaMilisegundos),
    diasRestantes: diasRestantes,
  });
  if (diasRestantes > 0) {
    return `Quedan ${diasRestantes} días`;
  } else if (diasRestantes === 0) {
    return 'Hoy es el último día';
  } else {
    return 'La oferta ha expirado';
  }
}
