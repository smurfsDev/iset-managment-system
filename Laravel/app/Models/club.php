<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class club extends Model
{
    use HasFactory;
    protected $table = 'clubs';
    // protected $fillable = [
    //     "nom",
    //     "dateCreation",
    //     "responsableClub",
    //     "president",
    //     "vicePresident"
    // ];
    protected $guarded = [];

    public function demandeCreationClub()
    {
        return $this->HasOne(DemandeCreationClub::class,'id','demandeCreationClubId');
    }

    public function demandeAdhesionClub(){
        return $this->HasMany('App\Models\DemandeAdhesionClub', 'club_id');
    }

    public function abouts(){
        return $this->HasMany('App\Models\about','idClub');
    }

    public function fondator(){
        return $this->belongsTo('App\Models\User','id');
    }
}
