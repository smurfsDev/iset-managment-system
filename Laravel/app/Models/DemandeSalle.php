<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DemandeSalle extends Model
{
    use HasFactory;
    protected $table='demande_salles';
    protected $fillable = [
        "idSalle",
        "idResponsable",
        "dateEmploi",
        "dateDeRemise",

    ];
    public function salles(){
        return $this->belongsTo(Salle::class,'idSalle');
    }



}
