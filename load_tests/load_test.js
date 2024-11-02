import http from 'k6/http';
import { check, sleep } from 'k6';
import { Counter } from 'k6/metrics';

// Configura o teste para 500 usuários virtuais por 5 minutos usando um ramp up e down
export const options = {
    stages: [
        { duration: '1m', target: 300 }, // Ramp up de 300 usuários simultâneos
        { duration: '1m', target: 400 }, // Aumenta para 400 usuários
        { duration: '1m', target: 500 }, // Alcança o pico de 500 usuários
        { duration: '1m', target: 400 }, // Reduz para 400 usuários
        { duration: '1m', target: 300 }, // Ramp down para 300 usuários
    ]
};

const BASE_URL = 'https://jsonplaceholder.typicode.com';
const errorCount = new Counter('errors');

export default function () {
    // Teste de GET
    let getResponse = http.get(`${BASE_URL}/posts/1`);
    check(getResponse, {
        'status was 200': (r) => r.status === 200,
    }) || errorCount.add(1);

    // Teste de POST
    let postResponse = http.post(`${BASE_URL}/posts`, JSON.stringify({
        title: 'foo',
        body: 'bar',
        userId: 1,
    }), { headers: { 'Content-Type': 'application/json' } });
    check(postResponse, {
        'status was 201': (r) => r.status === 201,
    }) || errorCount.add(1);

    // Teste de PUT
    let putResponse = http.put(`${BASE_URL}/posts/1`, JSON.stringify({
        id: 1,
        title: 'updated title',
        body: 'updated body',
        userId: 1,
    }), { headers: { 'Content-Type': 'application/json' } });
    check(putResponse, {
        'status was 200': (r) => r.status === 200,
    }) || errorCount.add(1);

    // Teste de DELETE
    let deleteResponse = http.del(`${BASE_URL}/posts/1`);
    check(deleteResponse, {
        'status was 200': (r) => r.status === 200,
    }) || errorCount.add(1);

    sleep(0.01); // Espera 10 milissegundos entre requisições
}
