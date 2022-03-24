<?php

use Illuminate\Http\Request;

use Illuminate\Support\Facades\Route;

use App\Http\Controllers\HeadersController;
use App\Http\Controllers\DemandeCreationClubController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
Route::group(['prefix'=>'/dcc'],function(){
    Route::get('/',[DemandeCreationClubController::class,'show']);
    Route::get('/{id}',[DemandeCreationClubController::class,'showMyDemandes']);
    Route::post('/',[DemandeCreationClubController::class,'create']);
    Route::put('/{id}',[DemandeCreationClubController::class,'update']);
    Route::delete('/{id}',[DemandeCreationClubController::class,'delete']);
    Route::put('/a/{id}',[DemandeCreationClubController::class,'accept']);
    Route::put('/d/{id}',[DemandeCreationClubController::class,'decline']);
});

Route::get('{idClub}/header/getAll',[HeadersController::class,'getHeaders']);
Route::post('{idClub}/header/create',[HeadersController::class,'createHeader']);
Route::delete('{idClub}/header/delete/{id}',[HeadersController::class,'deleteHeader']);
Route::put('{idClub}/header/update/{id}',[HeadersController::class,'updateHeader']);
