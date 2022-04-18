<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DemandeCreationClub extends Model
{    use HasFactory;
    protected $table='demande_creation_clubs';
    protected $fillable = [
        "nomClub",
        "logo",
        "dateCreation",
        "activite",
        "president",
        "vicePresident",
        "responsableClubId",
        "adminId",
        "status"
    ];
    public function responsableClub(){
        return $this->belongsTo(User::class,'id');
    }


}
