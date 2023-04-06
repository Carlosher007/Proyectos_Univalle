/** @format */


const { Router } = require('express');
const {
  nuevoEjerce,
  getLabores,
  nuevoContrato,
  buscarTrabajadores,
  infoContratoT,
  infoTransaccionT,
  notificacionesT,
  infoContratoC,
  infoTransaccionC,
  notificacionesC,
  calificarServicio,
  realizarPago,
  finalizarContrato,
  laboresDisponibles,
  infoServicio,
  obtenerFoto,
  obtenerRutaFoto,
} = require('../controllers/tasks.controller');

const router = Router();

router.post('/nuevoEjerce', nuevoEjerce);

router.get('/getLabores/:id',getLabores);

router.post('/nuevoContrato',nuevoContrato);

router.post('/buscarTrabajadores/:cid', buscarTrabajadores);

router.get('/infoContratoT/:tid',infoContratoT)

router.get('/infoTransaccionT/:tid',infoTransaccionT);

router.get('/notificacionesT/:tid',notificacionesT);

router.get('/infoContratoC/:cid',infoContratoC)

router.get('/infoTransaccionC/:cid',infoTransaccionC);

router.get('/notificacionesC/:cid',notificacionesC);

router.put('/calificarServicio',calificarServicio);

router.put('/realizarPago/:cid',realizarPago);

router.put('/finalizarContrato/:cid',finalizarContrato);

router.get('/laboresDisponibles',laboresDisponibles);

router.get('/infoServicio/:eid', infoServicio);

router.get('/foto/:tid', obtenerFoto)

router.get('/rutaFoto/:tid', obtenerRutaFoto)

module.exports = router;
